package platform.theme.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import platform.theme.domain.ThemeClient
import platform.theme.domain.ThemeSelected
import platform.theme.domain.ThemeType

class DataThemeClient(
    private val settings: Settings
) : ThemeClient {

    companion object {
        const val THEME_KEY = "theme-key"
        const val IS_DARK_KEY = "dark-key"
    }

    override suspend fun get(): ThemeSelected {
        val cacheThemeValue = settings[THEME_KEY, ""]
        val type = when(cacheThemeValue){
            ThemeType.DEFAULT.name -> ThemeType.DEFAULT
            ThemeType.PROTANOPIA.name -> ThemeType.PROTANOPIA
            ThemeType.TRITANOPIA.name -> ThemeType.TRITANOPIA
            ThemeType.DEUTERANOPIA.name -> ThemeType.DEUTERANOPIA
            ThemeType.ACHROMATOPSIA.name -> ThemeType.ACHROMATOPSIA
            else -> ThemeType.DEFAULT
        }
        return ThemeSelected(type)
    }

    override suspend fun save(themeType: ThemeType) {
        settings.putString(THEME_KEY, themeType.name)
    }
}