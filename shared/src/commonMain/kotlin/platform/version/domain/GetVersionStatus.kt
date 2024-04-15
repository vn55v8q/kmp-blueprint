package platform.version.domain
import platform.version.domain.entity.VersionStatus

class GetVersionStatus(
    private val remoteVersion: RemoteVersion,
    private val deviceVersion: DeviceVersion
) {
    suspend fun invoke() : VersionStatus {
        val remoteVersion = remoteVersion.getVersionApp()
        val deviceVersion = deviceVersion.getInstalledVersion()
        return VersionStatus(
            installed = deviceVersion,
            stable = remoteVersion.stableVersion,
            min = remoteVersion.minVersionSupported,
            isForceUpdate = IsMinVersion.invoke(deviceVersion, remoteVersion.minVersionSupported),
            isSoftUpdate = IsMinVersion.invoke(deviceVersion, remoteVersion.stableVersion)
        )
    }
}



