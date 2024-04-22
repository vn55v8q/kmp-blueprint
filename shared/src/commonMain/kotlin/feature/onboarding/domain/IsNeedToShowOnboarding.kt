package feature.onboarding.domain

class IsNeedToShowOnboarding(
    private val onboardingStorage: OnboardingStorage
) {
    suspend fun invoke() = onboardingStorage.isFinish().not()
}