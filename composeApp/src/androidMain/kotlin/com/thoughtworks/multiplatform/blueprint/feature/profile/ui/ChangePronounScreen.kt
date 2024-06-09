package com.thoughtworks.multiplatform.blueprint.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.TextStepComponent
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangePronounState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar

@Composable
fun ChangePronounScreen(
    modifier: Modifier,
    isLandscape: Boolean,
    state: ChangePronounState,
    onBackClick: () -> Unit,
    onChangePronoun: (String) -> Unit,
    onSavePronoun: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Pronombre",
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
            TextStepComponent(
                modifier = Modifier,
                isLandscape = isLandscape,
                isLoading = state.isLoading,
                title = "Ingrese Pronombres",
                label = "Pronombres",
                description = "Los datos son privados y almacenados de forma segura.",
                buttonText = "Guardar",
                name = state.pronoun,
                errorMessage = state.message,
                isEnabled = state.isLoading.not(),
                isValid = state.isValidPronoun,
                onNameChange = { newDescription ->
                    onChangePronoun(newDescription)
                },
                onConfirmName = {
                    onSavePronoun()
                }
            )
        }
        LaunchedEffect(key1 = state.isChangedSuccess) {
            if(state.isChangedSuccess){
                onFinish()
            }
        }
    }
}
