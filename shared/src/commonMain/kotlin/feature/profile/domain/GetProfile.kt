package feature.profile.domain

class GetProfile(
    private val profileClient: ProfileClient
) {
    suspend fun invoke() = profileClient.getProfile()
}

