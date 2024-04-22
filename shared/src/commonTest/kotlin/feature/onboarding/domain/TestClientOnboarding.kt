package feature.onboarding.domain

class TestClientOnboarding(
    private val onboarding: Onboarding
) : ClientOnboarding {
    override suspend fun get(): Onboarding {
        return onboarding
    }
}