package feature.account.domain

interface UserClient {
    suspend fun newUser(newUser: NewUser): Boolean
    suspend fun uploadImage(imageReference: ImageReference): UrlReference
}