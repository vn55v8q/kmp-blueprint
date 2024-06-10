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
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import platform.log.Log

@Composable
fun RegisterScreen(
    state: AccountState,
    onBackClick: () -> Unit,
    onUserClick: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onNameClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    onPasswordClick: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLastStepClick: () -> Unit,
    onLoginEmailClick: () -> Unit,
    onLoginPasswordClick: (String) -> Unit,
    onClickPasswordRecovery: () -> Unit,
    goToHomeScreen: () -> Unit
) {
    var user by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { 6 })

    LaunchedEffect(key1 = state.isCreateUser) {
        if(state.isCreateUser){
            goToHomeScreen()
        }
    }

    LaunchedEffect(key1 = state.isLoggedUser) {
        if(state.isLoggedUser){
            goToHomeScreen()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Registrate",
            showBackButton = true,
            onClickBack = {
                // TODO: Evaluar donde es la mejora clase para dejar esta logica
                if (state.currentStep > 0) {
                    onLastStepClick()
                } else {
                    onBackClick()
                }
            }
        )
    }, content = { padding ->
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
                        EmailStepComponent(modifier = Modifier.fillMaxWidth(),
                            email = email,
                            isValid = state.isValidEmail,
                            errorMessage = state.message.orEmpty(),
                            onEmailChange = { newEmail ->
                                email = newEmail
                            },
                            onConfirmEmail = { onEmailClick(email) })
                    }

                    1 -> {
                        NameStepComponent(modifier = Modifier.fillMaxWidth(),
                            name = name,
                            isValid = state.isValidName,
                            isLoading = state.isLoading,
                            errorMessage = state.message.orEmpty(),
                            onNameChange = { newName ->
                                name = newName
                                onNameChange(name)
                            },
                            onConfirmName = {
                                onNameClick(name)
                            })
                    }

                    2 -> {
                        UserStepComponent(modifier = Modifier.fillMaxWidth(),
                            user = user,
                            isValid = state.isValidUser,
                            errorMessage = state.message.orEmpty(),
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
                            isLoading = state.isLoading,
                            passwordStrength = state.passwordStrength,
                            onPasswordChange = { newPass ->
                                pass = newPass
                                onPasswordChange(newPass)
                            },
                            onConfirmPassword = {
                                onPasswordClick(pass)
                            })
                    }

                    4 -> {
                        LoginEmailStepComponent(modifier = Modifier.fillMaxWidth(),
                            email = email,
                            isValid = state.isValidEmail,
                            errorMessage = state.message.orEmpty(),
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
                            modifier = Modifier,
                            password = pass,
                            isValid = state.isValidPassword,
                            isLoading = state.isLoading,
                            errorMessage = state.message.orEmpty(),
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
            }

        }
    })


    BackHandler {
        val currentPace = pagerState.currentPage
        // TODO: Evaluar donde es la mejora clase para dejar esta logica
        if (currentPace > 0) {
            onLastStepClick()
        } else {
            onBackClick()
        }
    }

    LaunchedEffect(state.currentStep) {
        pagerState.animateScrollToPage(state.currentStep)
    }
}