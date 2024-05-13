package platform.validators.domain

import platform.log.Log

interface BlackListCacheClient<T> : BlackListClient<T> {
    
    suspend fun existsValueInBlackList(value: T): Boolean {
        val tag = "IsInvalidEmail"
        Log.d(tag, "init BlackListCacheClient : $value")
        Log.d(tag, "init BlackListCacheClient items size: ${getBlackList()}")
        val foundItem = getBlackList().find { item ->
            item?.equals(value) == true
        }
        return foundItem != null
    }

    suspend fun saveBlackList(list: List<T>, newVersion: Int) {
        mutablaList = list.toMutableList()
        version = newVersion
    }
}