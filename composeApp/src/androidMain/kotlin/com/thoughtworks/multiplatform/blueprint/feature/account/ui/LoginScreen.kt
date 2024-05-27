package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.LoginState
import platform.log.Log

@Composable
fun LoginScreen(
    state: LoginState,
    onLastStepClick: () -> Unit,
    onCloseScreen: () -> Unit,
    onEmailClick: (String) -> Unit,
    onPasswordClick: (String) -> Unit,
    onPasswordRecovery: () -> Unit,
    goToHomeScreen: () -> Unit
) {
    var email by remember { mutableStateOf("leratliciano@gmail.com") }
    var pass by remember { mutableStateOf("LeRatLuciano.123$") }
    val pagerState = rememberPagerState(pageCount = { 2 })

    LaunchedEffect(key1 = state.isLoggedUser) {
        if(state.isLoggedUser){
            goToHomeScreen()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), content = { padding ->
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                when (page) {
                    0 -> {
                        EmailStepComponent(
                            modifier = Modifier.fillMaxWidth(),
                            email = email,
                            isValid = state.isValidEmail,
                            errorMessage = state.errorMessage.orEmpty(),
                            onEmailChange = { newEmail ->
                                email = newEmail
                            },
                            onConfirmEmail = { onEmailClick(email) })
                    }

                    1 -> {
                        LoginPassStepComponent(
                            modifier = Modifier,
                            password = pass,
                            isValid = state.isValidPassword,
                            isLoading = state.isLoading,
                            errorMessage = state.errorMessage.orEmpty(),
                            onPasswordChange = { newPass ->
                                pass = newPass
                            },
                            onConfirmPassword = {
                                onPasswordClick(pass)
                            },
                            onClickPasswordRecovery = onPasswordRecovery
                        )
                    }

                    else -> {
                        Log.d("AccountScreen", "Page not found")
                    }
                }
            }

        }
    })


    BackHandler {
        val currentPace = pagerState.currentPage
        if (currentPace > 0) {
            onLastStepClick()
        } else {
            onCloseScreen()
        }
    }

    LaunchedEffect(state.currentStep) {
        pagerState.animateScrollToPage(state.currentStep)
    }
}