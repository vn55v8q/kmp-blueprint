package com.thoughtworks.multiplatform.blueprint.platform.designsystem.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TitleMediumText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp,
) {
    BaseText(
        modifier = modifier,
        text = text,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun TitleSmallText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp,
) {
    BaseText(
        modifier = modifier,
        text = text,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun TitleLargeText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp,
) {
    BaseText(
        modifier = modifier,
        text = text,
        showPadding = showPadding,
        textAlign = textAlign,
        paddingDefault = paddingDefault,
        style = MaterialTheme.typography.titleLarge
    )
}