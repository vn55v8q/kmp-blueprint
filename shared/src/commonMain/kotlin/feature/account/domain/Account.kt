package feature.account.domain

data class Account(
    val id: String,
    val name: String,
    val user: String,
    val pronoun: String,
    val description: String,
    //val urlAvatar: String
) {
    companion object {
        fun empty() = Account(
            "",
            "",
            "",
            "",
            "",
            //"",
        )
    }
}
