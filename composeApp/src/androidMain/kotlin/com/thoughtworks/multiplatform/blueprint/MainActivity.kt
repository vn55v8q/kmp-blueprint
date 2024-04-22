package com.thoughtworks.multiplatform.blueprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.ui.SplashScreen
import com.thoughtworks.multiplatform.blueprint.platform.navigation.BlueprintNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    // Lazy inject ViewModel
    private val splashViewModel: SplashViewModel by viewModel()
    private val onboardingViewModel: OnboardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlueprintNavigation(
                splashViewModel = splashViewModel,
                onboardingViewModel = onboardingViewModel
            ) {
                finish()
            }
        }
    }
}