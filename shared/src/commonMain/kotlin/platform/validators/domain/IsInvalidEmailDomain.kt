package platform.validators.domain

class IsInvalidEmailDomain(
    private val emailDomainUpdateLocalString: UpdateLocalString,
    private val local: BlackListCacheClient<String>
) {
    suspend fun invoke(emailDomain: String): Boolean {
        emailDomainUpdateLocalString.invoke()
        return local.existsValueInBlackList(emailDomain)
    }
}