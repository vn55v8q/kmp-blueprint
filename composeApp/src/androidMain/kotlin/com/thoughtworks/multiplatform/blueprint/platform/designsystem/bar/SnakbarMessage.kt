package com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText

@Composable
fun SnackbarMessage(
    text: String,
    textButton: String,
    modifier: Modifier = Modifier,
    onHide: () -> Unit
) {
    Snackbar(
        modifier = modifier
            .background(color = Color.White)
            .border(2.dp, MaterialTheme.colorScheme.secondary)
            .padding(12.dp),
        containerColor = Color.White,
        action = {
            TextButton(
                onClick = { onHide() },
            ) {
                BodyMediumText(textButton)
            }
        }
    ) {
        BodyMediumText(text)
    }
}