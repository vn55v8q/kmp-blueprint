package feature.onboarding.domain

interface OnboardingStorage {
    suspend fun saveComplete(date: String)
    suspend fun isFinish(): Boolean
}
