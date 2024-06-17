package com.thoughtworks.multiplatform.blueprint.feature.avatar.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.UpdateImageState
import feature.account.domain.TypeImage

@Composable
fun ImageSelectScreen(
    modifier: Modifier,
    state: UpdateImageState,
    onProcessImage: (String, TypeImage) -> Unit,
    onBackClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        ImageSelectLandscapeScreen(
            modifier = modifier,
            state = state,
            onProcessImage = onProcessImage,
            onBackClick = onBackClick
        )
    } else {
        ImageSelectPortraitScreen(
            modifier = modifier,
            state = state,
            onProcessImage = onProcessImage,
            onBackClick = onBackClick
        )
    }
}