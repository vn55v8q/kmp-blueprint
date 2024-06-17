package feature.profile.domain

class ChangeName(
    private val client: ProfileClient
) {
    suspend fun invoke(name: String) = client.changeName(name)
}