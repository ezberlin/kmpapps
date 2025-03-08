package io.github.ezberlin.vplanx.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestData(
    val Data: String,
    val DataType: Int
)