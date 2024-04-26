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
            getThirdPage(),
        )
    }

    private fun getFirstPage(): PageOnboarding {
        return PageOnboarding(
            id = "page1",
            name = "Page one",
            url = "https://developer.android.com/static/codelabs/jetpack-compose-animation/img/jetpack_compose_logo_with_rocket_856.png"
        )
    }

    private fun getSecondPage(): PageOnboarding {
        return PageOnboarding(
            id = "page2",
            name = "Page two",
            url = "https://cdn.hashnode.com/res/hashnode/image/upload/v1627633315818/vRdBa84mG.png"
        )
    }

    private fun getThirdPage(): PageOnboarding {
        return PageOnboarding(
            id = "page3",
            name = "Page third",
            url = "https://bignerdranch.com/wp-content/uploads/2021/05/swiftui-logo.jpg"
        )
    }
}