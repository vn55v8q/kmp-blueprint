package com.thoughtworks.multiplatform.blueprint.platform.designsystem.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    showPadding: Boolean = false,
    paddingDefault: Dp = 16.dp,
    style: TextStyle
) {
    val padding = if (showPadding) paddingDefault else 0.dp
    Text(
        modifier = modifier.padding(horizontal = padding),
        text = text,
        color = color,
        style = style
    )
}