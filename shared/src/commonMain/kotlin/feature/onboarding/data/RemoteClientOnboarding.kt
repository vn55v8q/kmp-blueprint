package feature.onboarding.data

import feature.onboarding.domain.ClientOnboarding
import feature.onboarding.domain.Onboarding

class RemoteClientOnboarding : ClientOnboarding {
    override suspend fun get(): Onboarding {
        return Onboarding.empty()
    }
}
