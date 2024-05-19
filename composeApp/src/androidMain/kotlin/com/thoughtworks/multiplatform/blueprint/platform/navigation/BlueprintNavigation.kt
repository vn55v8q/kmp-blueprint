package com.thoughtworks.multiplatform.blueprint.platform.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.LoginViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.AccountScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.LoginScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.RegisterScreen
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui.OnboardingScreen
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.ui.SplashScreen

@Composable
fun BlueprintNavigation(
    splashViewModel: SplashViewModel,
    onboardingViewModel: OnboardingViewModel,
    accountViewModel: AccountViewModel,
    loginViewModel: LoginViewModel,
    onFinish: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "register") {
        composable("splash") {
            val state = splashViewModel.state.collectAsState()
            SplashScreen(modifier = Modifier.fillMaxSize(),
                state = state.value,
                onNavigateToOnboarding = {
                    navController.navigate("onboarding")
                })
        }
        composable("onboarding") {
            LaunchedEffect(Unit) {
                onboardingViewModel.fetch()
            }
            val state = onboardingViewModel.state.collectAsState()
            OnboardingScreen(
                state = state.value, onFinish = onFinish
            )
        }
        composable("account") {
            AccountScreen(
                modifier = Modifier.fillMaxSize(),
                onClickLogin = {
                    navController.navigate("login")
                }, onClickRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            val state = accountViewModel.state.collectAsState()
            RegisterScreen(
                state = state.value,
                onBackClick = {
                    navController.popBackStack()
                },
                onUserClick = { newUser ->
                    accountViewModel.processUser(newUser)
                },
                onNameClick = { newName ->
                    accountViewModel.processName(newName)
                },
                onEmailClick = { newEmail ->
                    accountViewModel.processEmail(newEmail)
                },
                onPasswordClick = { newPass ->
                    accountViewModel.confirmPass(newPass)
                },
                onPasswordChange = { newPass ->
                    accountViewModel.processPass(newPass)
                },
                onHideSnackbar = {
                    accountViewModel.clearErrorMessage()
                },
                onLastStepClick = {
                    accountViewModel.onLastStepProcess()
                },
                onLoginEmailClick = {
                    accountViewModel.emailLoginConfirm()
                },
                onLoginPasswordClick = { password ->
                    accountViewModel.passwordLoginConfirm(password)
                },
                onClickPasswordRecovery = {},
            )
        }
        composable("login") {
            val state by loginViewModel.state.collectAsState()
            LoginScreen(
                state = state,
                onEmailClick = { email ->
                    loginViewModel.processEmail(email)
                },
                onPasswordClick = { password ->
                    loginViewModel.processPass(password)
                },
                onHideSnackbar = {
                    loginViewModel.clearErrorMessage()
                },
                onLastStepClick = {
                    loginViewModel.onLastStepProcess()
                },
                onCloseScreen = onFinish,
                onClickPasswordRecovery = {}
            )
        }
    }
} 