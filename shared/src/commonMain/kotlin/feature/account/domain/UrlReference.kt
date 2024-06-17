package feature.account.domain

class UrlReference(
    val url: String
) {
    companion object {
        fun empty() = UrlReference("")
    }
}