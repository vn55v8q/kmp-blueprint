package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileStep
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileViewModel
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import platform.log.Log

@Composable
fun ProfileScene(
    profileViewModel: ProfileViewModel,
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
                        onClickEditProfile = profileViewModel::nextScreen,
                        onClickChangeImage = profileViewModel::onClickChangeImage,
                        onBackClick = profileViewModel::previousScreen
                    )
                }

                1 -> {
                    TitleMediumText(text = "TODO Edit")
                }

                2 -> {
                    ImageSelectScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onProcessImage = profileViewModel::uploadImage,
                        onBackClick = profileViewModel::previousScreen
                    )
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
        }
        pagerHorizontalState.animateScrollToPage(newPage)
    }

    BackHandler {
        profileViewModel.previousScreen()
    }
}