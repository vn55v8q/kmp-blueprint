package feature.onboarding.domain

interface ClientOnboarding {
    suspend fun get(): Onboarding
}
