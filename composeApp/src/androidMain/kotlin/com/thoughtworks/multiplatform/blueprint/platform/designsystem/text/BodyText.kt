package com.thoughtworks.multiplatform.blueprint.platform.designsystem.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
        //color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.bodyMedium
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
        //color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun BodyLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp
) {
    BaseText(
        modifier = modifier,
        text = text,
        //color = color,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.bodyLarge
    )
}