package feature.avatar.domain

data class Avatar(
    val name: String, val urlImage: String, val user: String
) {
    companion object {
        fun empty(): Avatar {
            return Avatar("", "", "")
        }
    }
}