package feature.account.domain

interface UserClient {
    // TODO: Implementar logica que permita obtener el ID desde un Shared Preferences para evitar consultas a Firestore
    suspend fun getId(): String
    suspend fun newUser(newUser: NewUser): Boolean

    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun getAccount(): Account
    suspend fun isAuthenticatedUser() : Boolean

}