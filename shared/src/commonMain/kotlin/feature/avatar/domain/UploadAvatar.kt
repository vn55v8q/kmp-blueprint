package feature.avatar.domain

import feature.account.domain.ImageReference
import feature.account.domain.UrlReference

class UploadAvatar(
    private val avatarClient: AvatarClient
) {
    suspend fun invoke(imageReference: ImageReference): UrlReference {
        if(imageReference.rawUri.isEmpty()) {
            throw EmptyUriException()
        }
        return avatarClient.uploadImage(imageReference)
    }
}

