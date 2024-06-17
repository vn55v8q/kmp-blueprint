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
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeDescriptionState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar

@Composable
fun ChangeDescriptionScreen(
    modifier: Modifier,
    isLandscape: Boolean,
    state: ChangeDescriptionState,
    onBackClick: () -> Unit,
    onChangeDescription: (String) -> Unit,
    onSaveDescription: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Descripción Usuario",
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
                title = "Ingrese Descripción",
                label = "Description",
                description = "Los datos son privados y almacenados de forma segura.",
                buttonText = "Guardar",
                name = state.description,
                errorMessage = state.message,
                isEnabled = state.isLoading.not(),
                isValid = state.isValidDescription,
                onNameChange = { newDescription ->
                    onChangeDescription(newDescription)
                },
                onConfirmName = {
                    onSaveDescription()
                }
            )
        }
        LaunchedEffect(key1 = state.isChangedSuccess) {
            if (state.isChangedSuccess) {
                onFinish()
            }
        }
    }
}
