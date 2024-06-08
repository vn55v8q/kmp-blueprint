package feature.profile.domain

class ChangePronoun(
    private val client: ProfileClient
) {
    suspend fun invoke(pronoun: String) = client.changePronoun(pronoun)
}