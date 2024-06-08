package feature.profile.domain

class ChangeDescription(
    private val client: ProfileClient
) {
    suspend fun invoke(description: String) = client.changeDescription(description)
}