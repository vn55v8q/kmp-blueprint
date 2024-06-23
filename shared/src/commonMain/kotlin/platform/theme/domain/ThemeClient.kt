package platform.theme.domain

interface ThemeClient {
    fun get() : ThemeSelected
    suspend fun save(themeType: ThemeType, isDark: Boolean)
}