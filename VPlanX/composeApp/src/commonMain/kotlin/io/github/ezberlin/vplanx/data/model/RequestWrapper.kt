package io.github.ezberlin.vplanx.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestWrapper(
    val req: RequestData
)