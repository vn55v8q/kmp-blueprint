package platform.theme.domain

data class ThemeSelected(
    val type: ThemeType = ThemeType.DEFAULT,
    val isDark: Boolean = false
)

enum class ThemeType {
    DEFAULT, DEUTERANOPIA, PROTANOPIA, TRITANOPIA, ACHROMATOPSIA
}
