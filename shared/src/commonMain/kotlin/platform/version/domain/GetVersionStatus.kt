package platform.version.domain

import platform.version.domain.entity.VersionStatus

class GetVersionStatus(
    private val remoteVersion: RemoteVersion,
    private val deviceVersion: DeviceVersion
) {
    suspend fun invoke(): VersionStatus {
        return try {
            val remoteVersion = remoteVersion.getVersionApp()
            val deviceVersion = deviceVersion.getInstalledVersion()
            VersionStatus(
                installed = deviceVersion,
                stable = remoteVersion.stableVersion,
                min = remoteVersion.minVersion,
                isForceUpdate = IsMinVersion.invoke(deviceVersion, remoteVersion.minVersion),
                isSoftUpdate = IsMinVersion.invoke(deviceVersion, remoteVersion.stableVersion)
            )
        } catch (e: Exception) {
            val error = e.message
            print(error)
            VersionStatus.empty()
        }
    }
}



