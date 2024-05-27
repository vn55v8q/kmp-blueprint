package feature.account.domain

class GetAccount(
    private val userClient: UserClient
) {
    suspend fun invoke() = userClient.getAccount()
}