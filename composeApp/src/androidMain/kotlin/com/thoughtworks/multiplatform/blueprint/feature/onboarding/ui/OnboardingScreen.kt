package com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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
    val isShowLoading = state.onboarding == null
    if (isShowLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
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
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), Alignment.Center) {
                    val density = LocalDensity.current
                    AnimatedVisibility(
                        modifier = Modifier.height(40.dp),
                        visible = isShowFinish,
                        enter = slideInVertically {
                            // Slide in from 40 dp from the top.
                            with(density) { -40.dp.roundToPx() }
                        } + expandVertically(
                            // Expand from the top.
                            expandFrom = Alignment.Top
                        ) + fadeIn(
                            // Fade in with the initial alpha of 0.3f.
                            initialAlpha = 0.3f
                        ),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Button(
                            modifier = Modifier.height(40.dp),
                            onClick = onFinish
                        ) {
                            Text("Finish")
                        }
                    }
                }
                if (isShowFinish.not()) {
                    Spacer(Modifier.height(56.dp))
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