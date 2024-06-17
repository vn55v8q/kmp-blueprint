package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightScheme = lightColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryLight,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryLight,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerLight,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerLight,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryLight,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryLight,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerLight,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerLight,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryLight,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryLight,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerLight,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorLight,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorLight,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerLight,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerLight,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundLight,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundLight,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceLight,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceLight,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantLight,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantLight,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineLight,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantLight,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimLight,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceLight,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceLight,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryLight,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimLight,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowLight,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryDark,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryDark,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerDark,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerDark,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryDark,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryDark,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerDark,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerDark,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryDark,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryDark,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerDark,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorDark,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorDark,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerDark,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerDark,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundDark,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundDark,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDark,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceDark,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantDark,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantDark,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineDark,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantDark,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimDark,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceDark,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceDark,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryDark,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimDark,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowDark,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryLightMediumContrast,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryLightMediumContrast,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryLightMediumContrast,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerLightMediumContrast,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorLightMediumContrast,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorLightMediumContrast,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerLightMediumContrast,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundLightMediumContrast,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundLightMediumContrast,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceLightMediumContrast,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineLightMediumContrast,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantLightMediumContrast,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimLightMediumContrast,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryLightHighContrast,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryLightHighContrast,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryLightHighContrast,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerLightHighContrast,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorLightHighContrast,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorLightHighContrast,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerLightHighContrast,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundLightHighContrast,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundLightHighContrast,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceLightHighContrast,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantLightHighContrast,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineLightHighContrast,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantLightHighContrast,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimLightHighContrast,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryDarkMediumContrast,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryDarkMediumContrast,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorDarkMediumContrast,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorDarkMediumContrast,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerDarkMediumContrast,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundDarkMediumContrast,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundDarkMediumContrast,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDarkMediumContrast,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineDarkMediumContrast,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantDarkMediumContrast,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimDarkMediumContrast,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryDarkHighContrast,
    onPrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryDarkHighContrast,
    onSecondary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryDarkHighContrast,
    onTertiary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onTertiaryContainerDarkHighContrast,
    error = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorDarkHighContrast,
    onError = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorDarkHighContrast,
    errorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onErrorContainerDarkHighContrast,
    background = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.backgroundDarkHighContrast,
    onBackground = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onBackgroundDarkHighContrast,
    surface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDarkHighContrast,
    onSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineDarkHighContrast,
    outlineVariant = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.outlineVariantDarkHighContrast,
    scrim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.scrimDarkHighContrast,
    inverseSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.inversePrimaryDarkHighContrast,
    surfaceDim = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceDimDarkHighContrast,
    surfaceBright = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.AppTypography,
    content = content
  )
}

