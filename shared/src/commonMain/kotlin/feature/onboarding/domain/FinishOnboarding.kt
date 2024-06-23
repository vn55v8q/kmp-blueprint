package feature.onboarding.domain

class FinishOnboarding(
    private val database: OnboardingStorage
) {
    suspend fun invoke(date: String) {
        if (database.isFinish().not()) {
            database.saveComplete(date)
        }
    }
}
