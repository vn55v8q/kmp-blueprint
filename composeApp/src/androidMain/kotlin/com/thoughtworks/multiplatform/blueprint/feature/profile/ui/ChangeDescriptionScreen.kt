package com.thoughtworks.multiplatform.blueprint.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.UserStepComponent
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeDescriptionState
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeUserState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import feature.profile.domain.ChangeDescription

@Composable
fun ChangeDescriptionScreen(
    modifier: Modifier,
    state: ChangeDescriptionState,
    onBackClick: () -> Unit,
    onSaveDescription: (String) -> Unit,
    onFinish: () -> Unit
) {
    var user by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Usuario",
            showBackButton = true,
            onClickBack = onBackClick
        )
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // TODO: Acá quede
            Text(text = "TODO: Input description")
        }
        LaunchedEffect(key1 = state.isChangedSuccess) {
            if(state.isChangedSuccess){
                onFinish()
            }
        }

    }
}
