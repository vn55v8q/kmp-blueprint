package feature.account.domain

class UploadImage(
    private val userClient: UserClient
) {
    suspend fun invoke(imageReference: ImageReference): UrlReference {
        if(imageReference.rawUri.isEmpty()) {
            throw EmptyUriException()
        }
        return userClient.uploadImage(imageReference)
    }
}

class EmptyUriException : Exception("This uri is empty")