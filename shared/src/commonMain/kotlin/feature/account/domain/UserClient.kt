package feature.account.domain

interface UserClient {
    suspend fun newUser(newUser: NewUser): Boolean

    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun getAccount(): Account
    suspend fun isAuthenticatedUser() : Boolean

}