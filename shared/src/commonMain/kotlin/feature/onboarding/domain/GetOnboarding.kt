package feature.onboarding.domain

class GetOnboarding(
    private val remoteClient: ClientOnboarding,
    private val defaultClient: ClientOnboarding
) {
    suspend fun invoke(): Onboarding {
        val remote = remoteClient.get()
        val default = defaultClient.get()
        return if (remote.id.isNotEmpty()) {
            remote
        } else {
            return if (default.id.isEmpty()) {
                throw NotFoundOnboardingException(
                    listOf(remoteClient, defaultClient)
                )
            } else {
                default
            }
        }
    }
}
