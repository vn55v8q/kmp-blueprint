package platform.validators.domain

import platform.log.Log
import platform.validators.domain.exception.NotFoundVersionForClientExeption

class UpdateLocalString(
    private val remote: BlackListClient<String>,
    private val local: BlackListCacheClient<String>
) {
    suspend fun invoke() {
        val tag = "UpdateLocalString"
        Log.d(tag, "init")
        val remoteVersion = remote.syncAndGetVersion()
        Log.d(tag, "remoteVersion : $remoteVersion")
        val localVersion = local.syncAndGetVersion()
        Log.d(tag, "localVersion : $localVersion")

        if (remoteVersion == 0 && localVersion == 0) {
            Log.d(tag, "NotFoundVersionForClientExeption")
            throw NotFoundVersionForClientExeption()
        }

        if (localVersion < remoteVersion) {
            Log.d(tag, "remote.syncData()")
            remote.syncData()
            Log.d(tag, "saveBlackList : ${remote.getBlackList()}, $remoteVersion")
            local.saveBlackList(remote.getBlackList(), remoteVersion)
        }
    }
}