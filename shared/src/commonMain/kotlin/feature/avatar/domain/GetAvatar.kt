package feature.avatar.domain

class GetAvatar(
    private val avatarClient: AvatarClient
) {
    suspend fun invoke() = avatarClient.getAvatar()
}

