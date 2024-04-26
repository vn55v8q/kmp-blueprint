package feature.onboarding.domain

object FactoryTest {
    fun newFakeOnboarding(
        id: String = "1",
        name: String = "Fake Name",
        pages: List<PageOnboarding> = emptyList(),
        currentPage: Int = 0
    ): Onboarding {
        return Onboarding(
            id,
            name,
            pages,
            currentPage
        )
    }

    fun newClientOnboarding(
        id: String = "1",
        name: String = "Fake Name",
        pages: List<PageOnboarding> = emptyList(),
        currentPage: Int = 0
    ): ClientOnboarding {
        return TestClientOnboarding(
            newFakeOnboarding(
                id,
                name,
                pages,
                currentPage
            )
        )
    }
}