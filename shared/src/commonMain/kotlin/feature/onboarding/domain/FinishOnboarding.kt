package feature.onboarding.domain

class FinishOnboarding(
    private val database: OnboardingStorage
) {
    suspend fun invoke() {
        if (database.isFinish().not()) {
            database.saveComplete()
        }
    }
}
