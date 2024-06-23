package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.SmallSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleSmallText
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
import platform.theme.domain.ThemeType

@Composable
fun ThemeScreen(
    name: String,
    onClickItem: (ThemeType, Boolean) -> Unit,
    onFinishTheme: () -> Unit
) {
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Theme Manager $name",
            showBackButton = true,
            onClickBack = onFinishTheme
        )
    }, content = { padding ->
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            if (isLandscape) {
                ThemeScreenLandscape(
                    modifier = Modifier.fillMaxSize(), onClickItem = onClickItem
                )
            } else {
                ThemeScreenPortrait(
                    modifier = Modifier.fillMaxSize(), onClickItem = onClickItem
                )
            }
        }

    })
}

@Composable
fun ThemeScreenLandscape(
    modifier: Modifier = Modifier, onClickItem: (ThemeType, Boolean) -> Unit
) {
    Column(
        modifier
    ) {
        ColorSchemePreviewLandscapeRow(
            themeType = ThemeType.DEFAULT,
            lightScheme = lightScheme,
            darkScheme = darkScheme,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewLandscapeRow(
            themeType = ThemeType.PROTANOPIA,
            lightScheme = protanopiaScheme,
            darkScheme = darkSchemeProtanopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewLandscapeRow(
            themeType = ThemeType.TRITANOPIA,
            lightScheme = tritanopiaScheme,
            darkScheme = darkSchemeTritanopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewLandscapeRow(
            themeType = ThemeType.DEUTERANOPIA,
            lightScheme = deuteranopiaScheme,
            darkScheme = darkSchemeDeuteranopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewLandscapeRow(
            themeType = ThemeType.ACHROMATOPSIA,
            lightScheme = achromatopsiaScheme,
            darkScheme = darkSchemeAchromatopsia,
            onClick = onClickItem
        )
    }
}

@Composable
fun ThemeScreenPortrait(
    modifier: Modifier = Modifier,
    onClickItem: (ThemeType, Boolean) -> Unit
) {
    Column(
        modifier
    ) {
        ColorSchemePreviewPortraitRow(
            themeType = ThemeType.DEFAULT,
            lightScheme = lightScheme,
            darkScheme = darkScheme,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewPortraitRow(
            themeType = ThemeType.PROTANOPIA,
            lightScheme = protanopiaScheme,
            darkScheme = darkSchemeProtanopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewPortraitRow(
            themeType = ThemeType.TRITANOPIA,
            lightScheme = tritanopiaScheme,
            darkScheme = darkSchemeTritanopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewPortraitRow(
            themeType = ThemeType.DEUTERANOPIA,
            lightScheme = deuteranopiaScheme,
            darkScheme = darkSchemeDeuteranopia,
            onClick = onClickItem
        )
        SmallSpacer()
        ColorSchemePreviewPortraitRow(
            themeType = ThemeType.ACHROMATOPSIA,
            lightScheme = achromatopsiaScheme,
            darkScheme = darkSchemeAchromatopsia,
            onClick = onClickItem
        )
    }
}

@Composable
fun ColorSchemePreviewLandscapeRow(
    modifier: Modifier = Modifier,
    themeType: ThemeType,
    lightScheme: ColorScheme,
    darkScheme: ColorScheme,
    onClick: (ThemeType, Boolean) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        ColorSchemePreview(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onClick(themeType, false)
                }, themeType = themeType, colorScheme = lightScheme
        )
        ColorSchemePreview(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onClick(themeType, true)
                }, themeType = themeType, colorScheme = darkScheme, isDark = true
        )
    }
}

@Composable
fun ColorSchemePreviewPortraitRow(
    modifier: Modifier = Modifier,
    themeType: ThemeType,
    lightScheme: ColorScheme,
    darkScheme: ColorScheme,
    onClick: (ThemeType, Boolean) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ColorSchemePreview(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(themeType, false)
                }, themeType = themeType, colorScheme = lightScheme
        )
        ColorSchemePreview(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(themeType, true)
                }, themeType = themeType, colorScheme = darkScheme, isDark = true
        )
    }
}

@Composable
fun ColorSchemePreview(
    modifier: Modifier = Modifier,
    themeType: ThemeType,
    colorScheme: ColorScheme,
    isDark: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val name = if (isDark) themeType.name + " Dark" else themeType.name
        TitleSmallText(text = name)
        Row(modifier = Modifier.fillMaxWidth()) {
            ColorPreview(colorScheme.primary)
            ColorPreview(colorScheme.onPrimary)
            ColorPreview(colorScheme.primaryContainer)
            ColorPreview(colorScheme.onPrimaryContainer)
            ColorPreview(colorScheme.secondary)
            ColorPreview(colorScheme.onSecondary)
            ColorPreview(colorScheme.onSecondaryContainer)
            ColorPreview(colorScheme.onBackground)
            ColorPreview(colorScheme.onSurface)
            ColorPreview(colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun ColorPreview(color: Color, modifier: Modifier = Modifier.size(40.dp)) {
    Box(
        modifier = modifier.background(color)
    )
}