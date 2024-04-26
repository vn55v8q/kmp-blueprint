package feature.onboarding.data

import com.russhwolf.settings.Settings
import feature.onboarding.domain.OnboardingStorage
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class SharedOnboardingStore(
    private val settings: Settings
) : OnboardingStorage {
    private val checkKey = "check"
    private val checkDate = "date"
    override suspend fun saveComplete() {
        val now: Instant = Clock.System.now()
        settings.putBoolean(checkKey, true)
        settings.putString(checkDate, now.toString())
    }

    override suspend fun isFinish(): Boolean {
        return settings.getBoolean(checkKey, false)
    }

}
