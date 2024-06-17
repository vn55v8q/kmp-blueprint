package com.thoughtworks.multiplatform.blueprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme
import com.thoughtworks.multiplatform.blueprint.platform.navigation.BlueprintNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                BlueprintNavigation {
                    finish()
                }
            }
        }
    }
}