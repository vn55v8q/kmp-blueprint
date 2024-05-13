package com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewState

@Composable
fun OnboardingScreen(
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState
            ) { page ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // TODO: Manejar imagenes para dispositivos mas grandes como Tablet en Landscape
                    AsyncImage(
                        model = safeOnboarding.pages[page].url,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                            .background(Color.Black)
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp), contentAlignment = Alignment.BottomCenter) {
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
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 56.dp), Alignment.Center) {
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

            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    isShowFinish = (page == safeOnboarding.pages.size - 1)
                }
            }
        }
    }
}