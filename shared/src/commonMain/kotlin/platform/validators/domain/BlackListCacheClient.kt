package platform.validators.domain

interface BlackListCacheClient<T> : BlackListClient<T> {
    
    suspend fun existsValueInBlackList(value: T): Boolean {
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