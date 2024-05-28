package feature.account.domain

class IsAvalaibleChangeName(
    private val userClient: UserClient
) {
    suspend fun invoke() = userClient.isChangeNameEnabled()
}