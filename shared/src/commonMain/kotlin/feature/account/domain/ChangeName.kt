package feature.account.domain

class ChangeName(
    private val userClient: UserClient
) {
    suspend fun invoke(name: String) = userClient.changeName(name)
}