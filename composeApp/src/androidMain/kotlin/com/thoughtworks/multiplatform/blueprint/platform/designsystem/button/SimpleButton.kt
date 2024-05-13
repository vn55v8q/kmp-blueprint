package com.thoughtworks.multiplatform.blueprint.platform.designsystem.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleButton(
    modifier: Modifier,
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(),
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        contentPadding = ButtonDefaults.ContentPadding,
        interactionSource = null
    ) {
        Text(text)
    }
}