package com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    state: OnboardingViewState,
    onFinish: () -> Unit
) {
    var isShowFinish by remember {
        mutableStateOf(false)
    }
    state.onboarding?.let { safeOnboarding ->
        val pagerState = rememberPagerState(pageCount = {
            safeOnboarding.pages.size
        })
        Scaffold(
            modifier = modifier,
            content = { padding ->
                HorizontalPager(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    state = pagerState
                ) { page ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        AsyncImage(
                            model = safeOnboarding.pages[page].url,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(400.dp)
                                .clip(CircleShape)
                                .background(Color.Black)
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.BottomCenter) {
                    Row(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(16.dp)
                            )
                        }
                    }
                }
            },
            bottomBar = {
                Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
                    if (isShowFinish) {
                        AnimatedVisibility(visible = true) {
                            Button(
                                modifier = Modifier.height(40.dp),
                                onClick = onFinish
                            ) {
                                Text("Finish")
                            }
                        }
                    } else {
                        Spacer(Modifier.height(40.dp))
                    }
                }
            }
        )
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                isShowFinish = (page == safeOnboarding.pages.size - 1)
            }
        }
    }
}