package feature.onboarding.domain

class NotFoundOnboardingException(
    private val clients: List<ClientOnboarding>
) : Exception(
    "No information was found in any of the client: [" +
            "$clients" +
            "]"
)