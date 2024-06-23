package feature.onboarding.data

import com.russhwolf.settings.Settings
import feature.onboarding.domain.OnboardingStorage

class SharedOnboardingStore(
    private val settings: Settings
) : OnboardingStorage {
    private val checkKey = "check"
    private val checkDate = "date"
    override suspend fun saveComplete(date: String) {
        try {
            settings.putBoolean(checkKey, true)
            settings.putString(checkDate, date)
        } catch (e: Exception) {
            // TODO : Add to Crashlytics
            e.printStackTrace()
        }
    }

    override suspend fun isFinish(): Boolean {
        return settings.getBoolean(checkKey, false)
    }

}
