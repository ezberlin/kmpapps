package io.github.ezberlin.vplanx

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

/**
 * Decompress with gzip via Korio.
 */
actual fun gzipDecompress(input: ByteArray): String {
    val byteStream = ByteArrayInputStream(input)
    return GZIPInputStream(byteStream)
        .bufferedReader(StandardCharsets.UTF_8)
        .use { it.readText() }
}

/**
 * Compress the given byte array using gzip via Korio.
 */
actual fun gzipCompress(input: String): ByteArray {
    val byteStream = ByteArrayOutputStream()
    GZIPOutputStream(byteStream)
        .bufferedWriter(StandardCharsets.UTF_8)
        .use { it.write(input) }
    return byteStream.toByteArray()
}