package platform.theme.domain

class SaveTheme(
    private val themeClient: ThemeClient,
    private val themeRemoteClient: ThemeRemoteClient
) {
    suspend fun invoke(type: ThemeType, isDark: Boolean) {
        // TODO: Discutir si conviene obtener el get remoto o el get local, ya que
        // al obtener el get local no podremos soportar que un usuario tenga multiples dispositivos
        // pero tampoco podemos abusar de los request remotos.
        val currentThem = themeClient.get()
        if (type != currentThem.type || currentThem.isDark != isDark) {
            themeClient.save(type, isDark)
            themeRemoteClient.save(type, isDark)
        }
    }
}
