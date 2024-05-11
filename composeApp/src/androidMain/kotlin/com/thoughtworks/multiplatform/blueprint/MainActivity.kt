package com.thoughtworks.multiplatform.blueprint

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.platform.navigation.BlueprintNavigation
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    // Lazy inject ViewModel
    private val splashViewModel: SplashViewModel by viewModel()
    private val onboardingViewModel: OnboardingViewModel by viewModel()
    private val accountViewModel: AccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueprintNavigation(
                splashViewModel = splashViewModel,
                onboardingViewModel = onboardingViewModel,
                accountViewModel = accountViewModel
            ) {
                finish()
            }
        }
    }
}