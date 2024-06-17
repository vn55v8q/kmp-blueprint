package feature.profile.domain

class ChangeDescription(
    private val client: ProfileClient
) {
    suspend fun invoke(description: String) = client.changeDescription(description)
}

class ValidateDescription {
    fun invoke(newDescription: String) : Boolean {
        // TODO: Add all logic for description
        return newDescription.isNotEmpty()
    }
}