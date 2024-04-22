package feature.onboarding.data

import feature.onboarding.domain.ClientOnboarding
import feature.onboarding.domain.Onboarding
import feature.onboarding.domain.PageOnboarding

class DefaultClientOnboarding : ClientOnboarding {
    override suspend fun get(): Onboarding {
        return Onboarding(
            id = "default",
            name = "Onboarding",
            getPages()
        )
    }

    private fun getPages(): List<PageOnboarding> {
        return listOf(
            getFirstPage(),
            getSecondPage(),
            getFirstPage(),
        )
    }

    private fun getFirstPage(): PageOnboarding {
        return PageOnboarding(
            id = "page1",
            name = "Page one",
            url = ""
        )
    }

    private fun getSecondPage(): PageOnboarding {
        return PageOnboarding(
            id = "page2",
            name = "Page two",
            url = ""
        )
    }

    private fun getThirdPage(): PageOnboarding {
        return PageOnboarding(
            id = "page3",
            name = "Page third",
            url = ""
        )
    }
}