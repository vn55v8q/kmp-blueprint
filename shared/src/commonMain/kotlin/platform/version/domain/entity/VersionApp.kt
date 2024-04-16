package platform.version.domain.entity

data class VersionApp(
    val stableVersion: String,
    val minVersion: String
) {
    companion object {
        fun empty(): VersionApp {
            return VersionApp("", "")
        }
    }
}