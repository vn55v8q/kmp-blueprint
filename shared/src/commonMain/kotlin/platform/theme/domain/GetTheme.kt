package platform.theme.domain

class GetTheme(
    private val themeClient: ThemeClient,
    private val themeRemoteClient: ThemeRemoteClient
) {
    suspend fun invoke() : ThemeSelected{
        return try {
            themeRemoteClient.get().also { remoteTheme ->
                themeClient.save(remoteTheme.type, remoteTheme.isDark)
            }
        }  catch (e: Exception){
            themeClient.get()
        }
    }
}

