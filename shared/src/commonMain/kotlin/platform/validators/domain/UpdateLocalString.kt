package platform.validators.domain

import platform.validators.domain.exception.NotFoundVersionForClientExeption

class UpdateLocalString(
    private val remote: BlackListClient<String>,
    private val local: BlackListCacheClient<String>
) {
    suspend fun invoke() {
        val remoteVersion = remote.syncAndGetVersion()
        val localVersion = local.syncAndGetVersion()

        if (remoteVersion == 0 && localVersion == 0) {
            throw NotFoundVersionForClientExeption()
        }

        if (localVersion < remoteVersion) {
            remote.syncData()
            local.saveBlackList(remote.getBlackList(), remoteVersion)
        }
    }
}