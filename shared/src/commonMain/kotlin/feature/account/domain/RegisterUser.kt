package feature.account.domain

class RegisterUser(
    private val userClient: UserClient
) {
    suspend fun invoke(newUser: NewUser) = userClient.newUser(newUser)
}

class UploadImage(
    private val userClient: UserClient
) {
    suspend fun invoke(imageReference: ImageReference) = userClient.uploadImage(imageReference)
}

interface UserClient {
    suspend fun newUser(newUser: NewUser): Boolean
    suspend fun uploadImage(imageReference: ImageReference): UrlReference
}

enum class TypeImage {
    JPEG, PNG, SVG
}

class UrlReference(
    val url: String
)

class ImageReference(
    val id: String,
    val name: String,
    val type: TypeImage,
    val path: String
)