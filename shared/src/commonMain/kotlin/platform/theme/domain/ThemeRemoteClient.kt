package platform.theme.domain

interface ThemeRemoteClient {
    suspend fun save(themeType: ThemeType, isDark: Boolean)
    suspend fun get() : ThemeSelected
}