package feature.profile.domain

class ChangePronoun(
    private val client: ProfileClient
) {
    suspend fun invoke(pronoun: String) = client.changePronoun(pronoun)
}

class ValidatePronoun {
    fun invoke(pronoun: String) : Boolean {
        // TODO : Add all logic to validate pronouns
        return pronoun.isNotEmpty()
    }
}