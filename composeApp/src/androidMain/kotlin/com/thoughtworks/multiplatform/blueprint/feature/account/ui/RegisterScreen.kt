package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountState
import platform.log.Log

@Composable
fun RegisterScreen(
    state: AccountState,
    onUserClick: (String) -> Unit,
    onNameClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    onPasswordClick: (String) -> Unit,
    onHideSnackbar: () -> Unit,
    onLastStepClick: () -> Unit,
    onCloseScreen: () -> Unit,
    onLoginEmailClick: () -> Unit,
    onLoginPasswordClick: (String) -> Unit,
    onClickPasswordRecovery: () -> Unit
) {
    var user by remember { mutableStateOf("hardroid98") }
    var name by remember { mutableStateOf("Harttin Arce") }
    var email by remember { mutableStateOf("harttin.arce@gmail.com") }
    var pass by remember { mutableStateOf("123456") }
    val pagerState = rememberPagerState(pageCount = { 6 })
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.errorMessage) {
        val message = state.errorMessage.orEmpty()
        if (message.isNotEmpty()) {
            val reference = snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = true,
            )
            when (reference) {
                SnackbarResult.Dismissed -> onHideSnackbar()
                SnackbarResult.ActionPerformed -> onHideSnackbar()
            }
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
                            onEmailChange = { newEmail ->
                                email = newEmail
                            },
                            onConfirmEmail = { onEmailClick(email) })
                    }

                    1 -> {
                        NameStepComponent(modifier = Modifier.fillMaxWidth(),
                            name = name,
                            isValid = state.isValidName,
                            onNameChange = { newName ->
                                name = newName
                            },
                            onConfirmName = {
                                onNameClick(name)
                            })
                    }

                    2 -> {
                        UserStepComponent(modifier = Modifier.fillMaxWidth(),
                            user = user,
                            isValid = state.isValidUser,
                            onUserChange = { newUser ->
                                user = newUser
                            },
                            onConfirmUser = {
                                onUserClick(user)
                            })
                    }

                    3 -> {
                        PasswordStepComponent(modifier = Modifier.fillMaxWidth(),
                            password = pass,
                            isValid = state.isValidPassword,
                            onPasswordChange = { newPass ->
                                pass = newPass
                            },
                            onConfirmPassword = {
                                onPasswordClick(pass)
                            })
                    }

                    4 -> {
                        LoginEmailStepComponent(modifier = Modifier.fillMaxWidth(),
                            email = email,
                            isValid = state.isValidEmail,
                            onEmailChange = { newEmail ->
                                email = newEmail
                            },
                            onConfirmEmail = {
                                pass = ""
                                onLoginEmailClick()
                            })
                    }

                    5 -> {
                        LoginPassStepComponent(
                            modifier = Modifier.fillMaxWidth(),
                            password = pass,
                            isValid = state.isValidPassword,
                            onPasswordChange = { newPass ->
                                pass = newPass
                            },
                            onConfirmPassword = {
                                onLoginPasswordClick(pass)
                            },
                            onClickPasswordRecovery = onClickPasswordRecovery
                        )
                    }

                    else -> {
                        Log.d("AccountScreen", "Page not found")
                    }
                }
                SnackbarHost(snackbarHostState)
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