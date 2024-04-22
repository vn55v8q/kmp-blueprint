package com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    state: OnboardingViewState,
    onFinish: () -> Unit
) {
    state.onboarding?.let { safeOnboarding ->
        val pagerState = rememberPagerState(pageCount = {
            safeOnboarding.pages.size
        })
        Scaffold(
            content = { padding ->
                HorizontalPager(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    state = pagerState
                ) { page ->
                    Text(
                        text = "Page: $page",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            },
            bottomBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onFinish) {
                        Text("Finish")
                    }
                }
            }
        )
    }
}