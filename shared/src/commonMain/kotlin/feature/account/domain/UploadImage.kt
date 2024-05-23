package feature.account.domain

class UploadImage(
    private val userClient: UserClient
) {
    suspend fun invoke(imageReference: ImageReference) = userClient.uploadImage(imageReference)
}