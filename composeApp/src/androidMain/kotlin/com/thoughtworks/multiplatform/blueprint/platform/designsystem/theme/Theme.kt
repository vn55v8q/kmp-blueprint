package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.achromatopsia.achromatopsiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.achromatopsia.darkSchemeAchromatopsia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.deuteranopia.darkSchemeDeuteranopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.deuteranopia.deuteranopiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.oficial.darkScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.oficial.lightScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.protanopia.darkSchemeProtanopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.protanopia.protanopiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tritanopia.darkSchemeTritanopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tritanopia.tritanopiaScheme
import platform.theme.domain.ThemeSelected
import platform.theme.domain.ThemeType

@Immutable
data class ColorFamily(
    val color: Color, val onColor: Color, val colorContainer: Color, val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    themeState: ThemeSelected = ThemeSelected(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeState.type) {
        ThemeType.DEFAULT -> if (themeState.isDark) {
            darkScheme
        } else {
            lightScheme
        }

        ThemeType.DEUTERANOPIA -> if (themeState.isDark) {
            darkSchemeDeuteranopia
        } else {
            deuteranopiaScheme
        }

        ThemeType.PROTANOPIA -> if (themeState.isDark) {
            darkSchemeProtanopia
        } else {
            protanopiaScheme
        }
        ThemeType.TRITANOPIA -> if (themeState.isDark) {
            darkSchemeTritanopia
        } else {
            tritanopiaScheme
        }
        ThemeType.ACHROMATOPSIA -> if (themeState.isDark) {
            darkSchemeAchromatopsia
        } else {
            achromatopsiaScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = themeState.isDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(colorScheme),
        content = content
    )
}

