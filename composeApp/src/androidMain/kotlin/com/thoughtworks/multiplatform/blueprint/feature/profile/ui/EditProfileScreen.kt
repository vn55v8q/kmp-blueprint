package com.thoughtworks.multiplatform.blueprint.feature.profile.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ProfileState

@Composable
fun EditProfileScreen(
    modifier: Modifier,
    state: ProfileState,
    onBackClick: () -> Unit,
    onClickChangeImage: () -> Unit,
    onClickChangeName: () -> Unit,
    onClickChangeUser: () -> Unit,
    onClickChangePronoun: () -> Unit,
    onClickChangeDescription: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        EditProfileLandscapeScreen(
            modifier = modifier,
            state = state,
            onBackClick = onBackClick,
            onClickChangeImage = onClickChangeImage,
            onClickChangeName = onClickChangeName,
            onClickChangeUser = onClickChangeUser,
            onClickChangePronoun = onClickChangePronoun,
            onClickChangeDescription = onClickChangeDescription
        )
    } else {
        EditProfilePortraitScreen(
            modifier = modifier,
            state = state,
            onBackClick = onBackClick,
            onClickChangeImage = onClickChangeImage,
            onClickChangeName = onClickChangeName,
            onClickChangeUser = onClickChangeUser,
            onClickChangePronoun = onClickChangePronoun,
            onClickChangeDescription = onClickChangeDescription
        )
    }
}
