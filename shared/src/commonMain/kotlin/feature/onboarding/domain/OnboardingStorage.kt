package feature.onboarding.domain

interface OnboardingStorage {
    suspend fun saveComplete()
    suspend fun isFinish(): Boolean
}
