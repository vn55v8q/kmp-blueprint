package feature.profile.domain

class IsAvalaibleChangeName(
    private val client: ProfileClient
) {
    suspend fun invoke() = client.isChangeNameEnabled()
}