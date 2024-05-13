package feature.account.domain

data class NewUser(
    val user: String,
    val name: String,
    val email: String,
    val age: Int,
    val password: String,
) {
    companion object {
        fun default() = NewUser("", "", "", 0, "")
    }
}