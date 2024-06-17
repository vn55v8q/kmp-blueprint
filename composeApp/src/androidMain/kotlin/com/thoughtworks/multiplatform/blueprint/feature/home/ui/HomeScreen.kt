package com.thoughtworks.multiplatform.blueprint.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.home.presentation.HomeState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.image.CircularWebImage

@Composable
fun HomeScreen(
    modifier: Modifier,
    state: HomeState,
    onClickProfile: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                title = "Home",
                showBackButton = false,
                onClickBack = onBackClick
            )
        },
        content = { contentPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CircularWebImage(
                        modifier = Modifier.size(150.dp),
                        url = state.avatarUrl,
                        onClick = onClickProfile
                    )
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    SimpleButton(modifier = Modifier, text = "ENTER", isEnabled = state.isLoading.not()){

                    }
                }
            }
        },
        bottomBar = {

        }
    )
}