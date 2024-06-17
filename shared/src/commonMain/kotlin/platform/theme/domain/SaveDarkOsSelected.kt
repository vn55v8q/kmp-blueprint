package platform.theme.domain

class SaveDarkOsSelected(
    private val themeClient: ThemeClient
) {
    suspend fun invoke() {
        val selectedTheme = themeClient.get()
        if(selectedTheme.type == ThemeType.DEFAULT){
            themeClient.save(ThemeType.DARK)
        }
    }
}