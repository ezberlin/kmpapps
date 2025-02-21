package io.github.ezberlin.vplanx

import com.fleeksoft.charset.Charsets
import com.fleeksoft.charset.toByteArray
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.collections.List
import kotlin.collections.MutableMap
import kotlin.collections.drop
import kotlin.collections.first
import kotlin.collections.firstOrNull
import kotlin.collections.getOrNull
import kotlin.collections.indexOfFirst
import kotlin.collections.indices
import kotlin.collections.last
import kotlin.collections.lastOrNull
import kotlin.collections.listOf
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.set
import kotlin.collections.withIndex
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

expect fun gzipCompress(input: String): ByteArray

expect fun gzipDecompress(input: ByteArray): String

// ---------- Data Classes for JSON requests/response ----------
@Serializable
data class ApiParams(
    @SerialName("UserId") val userId: String,
    @SerialName("UserPw") val userPw: String,
    @SerialName("AppVersion") val appVersion: String,
    @SerialName("Language") val language: String,
    @SerialName("OsVersion") val osVersion: String,
    @SerialName("AppId") val appId: String,
    @SerialName("Device") val device: String,
    @SerialName("BundleId") val bundleId: String,
    @SerialName("Date") val date: String,
    @SerialName("LastUpdate") val lastUpdate: String
)

@Serializable
data class RequestData(
    val Data: String,
    val DataType: Int
)

@Serializable
data class RequestWrapper(
    val req: RequestData
)

// ---------- The APIHandler class ----------
class ApiHandler(
    username: String,
    password: String,
    private val tablemapper: List<String> =
        listOf("type", "class", "lesson", "subject", "room", "new_subject", "new_teacher", "teacher")
) {
    // Use kotlinx-datetime instead of java.time
    private val currentTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    // The formatting here produces an ISO string; append milliseconds and Z suffix as needed.
    private val formattedTime = "$currentTime.000Z"
    private val appVersion = "2.5.9"
    private val language = "de"
    private val osVersion = "28 8.0"
    // Use Kotlin’s UUID generation (from kotlin.random or platform-specific) – here we use the JVM’s
    @OptIn(ExperimentalUuidApi::class)
    private val appId = Uuid.random().toString()
    private val device = "SM-G930F"
    private val bundleId = "de.heinekingmedia.dsbmobile"
    private val dataURL = "https://app.dsbcontrol.de/JsonHandler.ashx/GetData"
    private var tableURL: String? = null

    private val classIndex: Int? =
        tablemapper.indexOfFirst { it == "class" }.takeIf { it >= 0 }

    private val params = ApiParams(
        userId = username,
        userPw = password,
        appVersion = appVersion,
        language = language,
        osVersion = osVersion,
        appId = appId,
        device = device,
        bundleId = bundleId,
        date = formattedTime,
        lastUpdate = formattedTime
    )

    /**
     * Extracts the URL from the JSON (multiplatform friendly) tree.
     *
     * Assumes that the structure is similar to the original version.
     */
    private fun extractUrlFromJson(jsonElement: kotlinx.serialization.json.JsonElement): String? {
        try {
            // Traverse the JSON structure.
            val rootObject = jsonElement.jsonObject
            val resultMenuItems = rootObject["ResultMenuItems"]?.jsonArray ?: return null
            val firstItem = resultMenuItems.first().jsonObject
            val childs = firstItem["Childs"]?.jsonArray ?: return null
            // get the last item
            val lastChild = childs.last().jsonObject
            val root = lastChild["Root"]?.jsonObject ?: return null
            val childChilds = root["Childs"]?.jsonArray ?: return null
            val firstChild = childChilds.first().jsonObject
            val detailsArray = firstChild["Childs"]?.jsonArray ?: return null
            val detail = detailsArray.first().jsonObject["Detail"]?.jsonPrimitive?.content
            return detail
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * Fetches data from the API and sets tableURL.
     */
    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun fetchData(client: HttpClient) {
        val json = Json { ignoreUnknownKeys = true }

        // Serialize parameters to JSON.
        val paramsJson = json.encodeToString(ApiParams.serializer(), params)
        val compressedBytes = gzipCompress(paramsJson)
        val paramsCompressed = Base64.encode(compressedBytes)

        val requestData = RequestData(Data = paramsCompressed, DataType = 1)
        val requestWrapper = RequestWrapper(req = requestData)

        val response: HttpResponse = client.post(dataURL) {
            contentType(ContentType.Application.Json)
            setBody(requestWrapper)
        }

        if (!response.status.isSuccess()) {
            throw Exception("Unexpected code $response")
        }

        val timetableData = response.bodyAsText()
        val outerJson = json.parseToJsonElement(timetableData).jsonObject
        val dataCompressedStr = outerJson["d"]?.jsonPrimitive?.content
            ?: throw Exception("Missing compressed timetable data")

        val compressedDataBytes = Base64.decode(dataCompressedStr.toByteArray(Charsets.UTF8))
        val decompressedDataBytes = gzipDecompress(compressedDataBytes)
        val decompressedDataStr = decompressedDataBytes

        val dataJson = json.parseToJsonElement(decompressedDataStr)
        tableURL = extractUrlFromJson(dataJson)
    }

    /**
     * Returns a list of timetable entries represented as maps.
     */
    fun fetchTimetable(): List<MutableMap<String, String>> = runBlocking {
        val results = mutableListOf<MutableMap<String, String>>()

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        try {
            fetchData(client)

            if (tableURL == null) {
                throw Exception("Table URL extraction failed")
            }

            // Obtain HTML. Note: ksoup accepts a String source.
            val htmlResponse: String = client.get(tableURL!!).bodyAsText()
            val document: Document = Ksoup.parse(htmlResponse)

            // Assuming your table structure is as before:
            val monLists = document.getElementsByClass("mon_list")
            val monHeads = document.getElementsByClass("mon_head")
            val monTitles = document.getElementsByClass("mon_title")

            for ((index, tableElement) in monLists.withIndex()) {
                // Get the update timestamp from the corresponding header.
                val headElement = monHeads.getOrNull(index)
                val spanElements = headElement?.getElementsByTag("span")
                val lastSpan = spanElements?.lastOrNull()
                // In ksoup the API is similar to Jsoup; adjust if needed.
                val updatesText = lastSpan?.nextSibling()?.toString() ?: ""
                val updates = if (updatesText.contains("Stand: ")) {
                    updatesText.split("Stand: ")[1]
                } else updatesText

                val titleElement = monTitles.getOrNull(index)
                val titles = titleElement?.text() ?: ""
                val date = titles.split(" ").getOrNull(0) ?: ""
                // Remove comma and extra spaces.
                val day = titles.split(" ").getOrNull(1)?.split(",")?.firstOrNull() ?: ""

                val rows = tableElement.getElementsByTag("tr").drop(1) // drop header row
                for (row in rows) {
                    val infos = row.getElementsByTag("td")
                    if (infos.size < 2) continue

                    // For the "class" column, split on ", " if possible.
                    val classes: List<String> = if (classIndex != null && classIndex < infos.size) {
                        infos[classIndex].text().split(", ")
                    } else {
                        listOf("---")
                    }

                    for (schoolclass in classes) {
                        val newEntry = mutableMapOf<String, String>()
                        newEntry["date"] = date
                        newEntry["day"] = day
                        newEntry["updated"] = updates

                        for (i in infos.indices) {
                            val attribute = if (i < tablemapper.size) tablemapper[i] else "col$i"
                            val cellText = infos[i].text()
                            newEntry[attribute] = if (cellText != "\u00a0") {
                                if (attribute == "class") schoolclass else cellText
                            } else {
                                "---"
                            }
                        }
                        results.add(newEntry)
                    }
                }
            }
        } finally {
            client.close()
        }
        results
    }
}
