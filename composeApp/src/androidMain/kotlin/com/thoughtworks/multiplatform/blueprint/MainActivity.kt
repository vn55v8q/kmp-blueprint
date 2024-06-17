package com.thoughtworks.multiplatform.blueprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.presentation.ThemeViewModel
import com.thoughtworks.multiplatform.blueprint.platform.navigation.BlueprintNavigation
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = koinViewModel()
            val themeState by themeViewModel.state.collectAsState()
            val isDarkThemeOsSelected = isSystemInDarkTheme()
            LaunchedEffect(key1 = Unit) {
                if(isDarkThemeOsSelected){
                    themeViewModel.notificationDarkOsSelected()
                }
            }
            AppTheme(
                themeState = themeState
            ) {
                BlueprintNavigation {
                    finish()
                }
            }
        }
    }
}