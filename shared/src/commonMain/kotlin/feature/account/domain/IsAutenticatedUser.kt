package feature.account.domain

class IsAutenticatedUser(
    private val userClient: UserClient
) {
    suspend fun invoke() = userClient.isAuthenticatedUser()
}