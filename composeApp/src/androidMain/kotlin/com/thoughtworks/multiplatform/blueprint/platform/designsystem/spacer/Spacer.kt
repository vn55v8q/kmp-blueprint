package com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.size(12.dp))
}

@Composable
fun LargeSpacer() {
    Spacer(modifier = Modifier.size(16.dp))
}