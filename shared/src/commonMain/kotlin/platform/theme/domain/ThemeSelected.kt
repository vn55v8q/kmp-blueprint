package platform.theme.domain

data class ThemeSelected(
    val type: ThemeType = ThemeType.DEFAULT
)

enum class ThemeType {
    DEFAULT, DARK, DEUTERANOPIA, PROTANOPIA, TRITANOPIA, ACHROMATOPSIA
}
