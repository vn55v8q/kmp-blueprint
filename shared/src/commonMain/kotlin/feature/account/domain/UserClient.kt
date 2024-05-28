package feature.account.domain

interface UserClient {
    suspend fun newUser(newUser: NewUser): Boolean
    suspend fun uploadImage(imageReference: ImageReference): UrlReference
    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun getAccount(): Account
    suspend fun changeName(newName: String): Boolean
    suspend fun isChangeNameEnabled() : Boolean
    suspend fun isChangeUserEnabled() : Boolean
    suspend fun isChangePronounEnabled() : Boolean
    suspend fun isChangeDescriptionEnabled() : Boolean
}