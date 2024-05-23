package platform.validators.domain

class IsInvalidName(
    private val local: BlackListCacheClient<String>,
) {
    suspend fun invoke(name: String, minLength: Int = 2): Boolean {
        return if(name.length >= minLength){
            val names = SplitName.invoke(name)
            names.forEach { nameItem ->
                val nameWithoutNumbers = ChangeNumberForLetter.invoke(nameItem).lowercase()
                if (local.existsValueInBlackList(nameWithoutNumbers)) {
                    return true
                }
            }
            false
        } else {
            true
        }
    }
}
