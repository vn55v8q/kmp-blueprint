package platform.validators.domain

class IsInvalidName(
    private val local: BlackListCacheClient<String>,
) {
    suspend fun invoke(name: String): Boolean {
        return if(name.length >= 2){
            local.existsValueInBlackList(name)
        } else {
            true
        }
    }
}
