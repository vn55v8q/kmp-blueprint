package feature.profile.domain

interface ProfileClient {
    suspend fun getProfile(): Profile
    suspend fun changeName(newName: String): Boolean
    suspend fun changeUser(newName: String): Boolean
    suspend fun changeDescription(newDescription: String): Boolean
    suspend fun changePronoun(newPronoun: String): Boolean
    suspend fun isChangeNameEnabled() : Boolean
    suspend fun isChangeUserEnabled() : Boolean
    suspend fun isChangePronounEnabled() : Boolean
    suspend fun isChangeDescriptionEnabled() : Boolean
}