package feature.account.domain

class LoginUser(
    private val userClient: UserClient
) {
    suspend fun invoke(email: String, password: String) : Boolean {
        return userClient.loginUser(email, password)
    }
}