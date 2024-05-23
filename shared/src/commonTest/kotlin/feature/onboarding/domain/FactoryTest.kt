package feature.onboarding.domain

object FactoryTest {
    fun newFakeOnboarding(
        id: String = "1",
        name: String = "Fake Name",
        pages: List<PageOnboarding> = emptyList()
    ): Onboarding {
        return Onboarding(
            id,
            name,
            pages
        )
    }

    fun newClientOnboarding(
        id: String = "1",
        name: String = "Fake Name",
        pages: List<PageOnboarding> = emptyList()
    ): ClientOnboarding {
        return TestClientOnboarding(
            newFakeOnboarding(
                id,
                name,
                pages
            )
        )
    }
}