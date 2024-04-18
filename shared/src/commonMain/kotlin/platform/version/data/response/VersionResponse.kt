package platform.version.data.response

import kotlinx.serialization.Serializable

@Serializable
data class VersionResponse(
    val version: VersionModel
)

@Serializable
data class VersionModel(
    val stable: String,
    val min: String
)