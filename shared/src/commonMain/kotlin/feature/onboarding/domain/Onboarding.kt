package feature.onboarding.domain

data class Onboarding(
    val id: String,
    val name: String,
    val pages: List<PageOnboarding>
) {
    companion object {
        fun empty() = Onboarding("", "", emptyList())
    }
}
