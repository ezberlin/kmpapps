package io.github.ezberlin.vplanx.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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