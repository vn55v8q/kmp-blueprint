package platform.theme.domain

class SaveTheme(
    private val themeClient: ThemeClient
) {
    suspend fun invoke(type: ThemeType) {
        if (type != themeClient.get().type) {
            themeClient.save(type)
        }
    }
}
