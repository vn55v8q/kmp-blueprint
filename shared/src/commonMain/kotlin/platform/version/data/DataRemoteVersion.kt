package platform.version.data

import platform.version.domain.RemoteVersion
import platform.version.domain.entity.VersionApp

class DataRemoteVersion : RemoteVersion {
    override suspend fun getVersionApp(): VersionApp {
        return VersionApp("1.0.1", "0.8.0")
    }

}