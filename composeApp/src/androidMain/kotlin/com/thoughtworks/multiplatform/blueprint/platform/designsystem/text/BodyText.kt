package com.thoughtworks.multiplatform.blueprint.platform.designsystem.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTypography

@Composable
fun BodyMediumText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp
) {
    BaseText(
        modifier = modifier,
        text = text,
        color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = AppTypography.bodyMedium
    )
}

@Composable
fun BodySmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp
) {
    BaseText(
        modifier = modifier,
        text = text,
        color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = AppTypography.bodySmall
    )
}

@Composable
fun BodyLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp
) {
    BaseText(
        modifier = modifier,
        text = text,
        color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = AppTypography.bodyLarge
    )
}