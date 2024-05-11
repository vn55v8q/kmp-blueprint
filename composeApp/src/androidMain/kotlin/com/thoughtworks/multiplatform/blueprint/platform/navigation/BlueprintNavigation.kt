package com.thoughtworks.multiplatform.blueprint.platform.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.AccountScreen
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui.OnboardingScreen
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.ui.SplashScreen

@Composable
fun BlueprintNavigation(
    splashViewModel: SplashViewModel,
    onboardingViewModel: OnboardingViewModel,
    accountViewModel: AccountViewModel,
    onFinish: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "account") {
        composable("splash") {
            val state = splashViewModel.state.collectAsState()
            SplashScreen(
                modifier = Modifier.fillMaxSize(),
                state = state.value,
                onNavigateToOnboarding = {
                    navController.navigate("onboarding")
                }
            )
        }
        composable("onboarding") {
            LaunchedEffect(Unit) {
                onboardingViewModel.fetch()
            }
            val state = onboardingViewModel.state.collectAsState()
            OnboardingScreen(
                state = state.value,
                onFinish = onFinish
            )
        }
        composable("account") {
            val state = accountViewModel.state.collectAsState()
            AccountScreen(
                modifier = Modifier.fillMaxSize(),
                state = state.value,
                onChangeName = accountViewModel::processName
            )
        }
        // Add more destinations similarly.
    }
} 