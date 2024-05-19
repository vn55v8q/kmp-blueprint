package com.thoughtworks.multiplatform.blueprint.platform.designsystem.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText

@Composable
fun LoadingButton(
    modifier: Modifier,
    text: String,
    isLoading: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled && isLoading.not(),
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(),
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        contentPadding = ButtonDefaults.ContentPadding,
        interactionSource = null
    ) {
        val textColor = if(isLoading.not() && isEnabled) Color.White else Color.Black
        BodyMediumText(text = text, color = textColor)
        MediumSpacer()
        AnimatedVisibility(visible = isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
