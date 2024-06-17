package platform.theme.domain

interface ThemeClient {
    suspend fun get() : ThemeSelected
    suspend fun save(themeType: ThemeType)
}