package feature.avatar.domain

import feature.account.domain.ImageReference
import feature.account.domain.UrlReference

interface AvatarClient {
    suspend fun getAvatar(): Avatar
    suspend fun uploadImage(imageReference: ImageReference): UrlReference
}