package platform.version.domain

import platform.version.domain.entity.VersionApp


interface RemoteVersion {
    suspend fun getVersionApp() : VersionApp
}
