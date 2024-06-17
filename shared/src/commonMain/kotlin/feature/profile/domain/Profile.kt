package feature.profile.domain

data class Profile(
    val name: String,
    val urlAvatar: String,
    val user: String,
    val pronoun: String,
    val description: String
) {
    companion object {
        fun empty(): Profile {
            return Profile(
                "",
                "",
                "",
                "",
                ""
            )
        }
    }
}