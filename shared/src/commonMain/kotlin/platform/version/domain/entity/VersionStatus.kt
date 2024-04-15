package platform.version.domain.entity

data class VersionStatus(
    val installed: String,
    val stable: String,
    val min: String,
    val isForceUpdate: Boolean,
    val isSoftUpdate: Boolean
)