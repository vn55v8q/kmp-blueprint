package platform.validators.domain

import platform.log.Log

class IsInvalidName(
    private val local: BlackListCacheClient<String>,
) {
    suspend fun invoke(name: String, minLength: Int = 2): Boolean {
        val tag = "IsInvalidEmail"
        Log.d(tag, "init name : $name")
        return if(name.length >= minLength){
            local.existsValueInBlackList(name)
        } else {
            true
        }
    }
}
