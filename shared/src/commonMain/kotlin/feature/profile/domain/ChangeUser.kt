package feature.profile.domain

class ChangeUser(
    private val client: ProfileClient
) {
    suspend fun invoke(name: String) = client.changeUser(name)
}