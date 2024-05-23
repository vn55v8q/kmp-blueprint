package platform.validators.domain

class IsInvalidEmailDotCom(
    private val dotComUpdateLocalString: UpdateLocalString,
    private val local: BlackListCacheClient<String>
) {
    suspend fun invoke(emailDomain: String): Boolean {
        dotComUpdateLocalString.invoke()
        return local.existsValueInBlackList(emailDomain)
    }
}