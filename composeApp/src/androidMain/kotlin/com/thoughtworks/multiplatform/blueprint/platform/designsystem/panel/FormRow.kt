package com.thoughtworks.multiplatform.blueprint.platform.designsystem.panel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText

@Composable
fun FormRow(
    modifier: Modifier,
    leftText: String,
    rightText: String,
    rightIcon: ImageVector,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyMediumText(
            text = leftText,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Left,
            showPadding = true
        )
        Row(
            modifier = Modifier.weight(1f).padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyMediumText(
                modifier = Modifier.weight(0.9f),
                text = rightText,
                textAlign = TextAlign.Right,
                showPadding = false
            )
            Icon(
                modifier = Modifier.weight(0.1f),
                imageVector = rightIcon,
                contentDescription = ""
            )
        }

    }
}