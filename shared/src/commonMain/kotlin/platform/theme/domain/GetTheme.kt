package platform.theme.domain

class GetTheme(
    private val themeClient: ThemeClient
) {
    suspend fun invoke() = themeClient.get()
}

