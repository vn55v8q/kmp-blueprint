package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ChangeNameViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileStep
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileViewModel
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyLargeText
import platform.log.Log

@Composable
fun ProfileScene(
    profileViewModel: ProfileViewModel,
    changeNameViewModel: ChangeNameViewModel,
    onFinishScene: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        Log.d("Profile", "Lauched Effect pre fetch")
        profileViewModel.fetch()
    }

    val state by profileViewModel.state.collectAsState()
    val pagerHorizontalState = rememberPagerState(pageCount = { ProfileStep.entries.size })

    HorizontalPager(
        modifier = Modifier
            .fillMaxSize(),
        state = pagerHorizontalState,
        userScrollEnabled = false
    ) { page ->
        Log.d("Profile", "HorizontalPager  page : $page")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            when (page) {
                0 -> {
                    AvatarScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onClickEditProfile = profileViewModel::clickProfile,
                        onClickChangeImage = profileViewModel::clickChangeImage,
                        onBackClick = profileViewModel::previousScreen
                    )
                }

                1 -> {
                    EditProfileScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onBackClick = profileViewModel::previousScreen,
                        onClickChangeImage = profileViewModel::clickChangeImage,
                        onClickChangeName = profileViewModel::clickChangeName,
                        onClickChangeUser = profileViewModel::clickChangeUser,
                        onClickChangePronoun = profileViewModel::clickChangePronoun,
                        onClickChangeDescription = profileViewModel::clickChangeDescription
                    )
                }

                2 -> {
                    ImageSelectScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onProcessImage = profileViewModel::uploadImage,
                        onBackClick = profileViewModel::previousScreen
                    )
                }

                3 -> {
                    val changeNameState by changeNameViewModel.state.collectAsState()
                    ChangeNameScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = changeNameState,
                        onBackClick = profileViewModel::previousScreen,
                        onChangeName = changeNameViewModel::processName,
                        onSaveName = changeNameViewModel::saveName
                    )
                }

                4 -> {
                    BodyLargeText(text = "Change User")
                }

                5 -> {
                    BodyLargeText(text = "Change Pronoun")
                }

                6 -> {
                    BodyLargeText(text = "Change Description")
                }
            }
        }
    }

    LaunchedEffect(key1 = state.isFinishFlow) {
        if (state.isFinishFlow) {
            onFinishScene()
        }
    }

    LaunchedEffect(key1 = state.currentStep) {
        val newPage = when (state.currentStep) {
            ProfileStep.DEFAULT -> 0
            ProfileStep.EDIT -> 1
            ProfileStep.UPLOAD_IMAGE -> 2
            ProfileStep.CHANGE_NAME -> 3
            ProfileStep.CHANGE_USER -> 4
            ProfileStep.CHANGE_PRONOUN -> 5
            ProfileStep.CHANGE_DESCRIPTION -> 6
        }
        pagerHorizontalState.animateScrollToPage(newPage)
    }

    BackHandler {
        profileViewModel.previousScreen()
    }
}