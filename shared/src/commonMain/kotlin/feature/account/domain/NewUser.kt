package feature.account.domain

data class NewUser(
    val name: String,
    val email: String,
    val age: Int,
    val password: String,
    val imageId: String
)