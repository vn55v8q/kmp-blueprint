package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ChangeNameState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.Toolbar
import platform.log.Log

@Composable
fun ChangeNameScreen(
    modifier: Modifier,
    state: ChangeNameState,
    onBackClick: () -> Unit,
    onChangeName: (String) -> Unit,
    onSaveName: (String) -> Unit,
    onFinish: () -> Unit
) {
    Log.d("ProfileScene", "ChangeNameScreen name: ${state.name}")
    var name by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Nombre",
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
            NameStepComponent(
                modifier = Modifier.fillMaxWidth(),
                name = name,
                isLoading = state.isLoading,
                isValid = state.isValidUser,
                errorMessage = state.message,
                description = "El nombre se puede cambiar una vez cada 7 días.",
                buttonText = "Guardar",
                onNameChange = { newName ->
                    name = newName
                    onChangeName(name)
                },
                onConfirmName = {
                    onSaveName(name)
                })
        }
        LaunchedEffect(key1 = state.isChangedSuccess) {
            if(state.isChangedSuccess){
                onFinish()
            }
        }

    }
}
