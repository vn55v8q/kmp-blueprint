package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.PaswordEditText

@Composable
fun PasswordStepComponent(
    modifier: Modifier,
    password: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onPasswordChange: (String) -> Unit,
    onConfirmPassword: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaswordEditText(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onPasswordChange
        )

        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = isEnabled,
            onClick = onConfirmPassword
        )
    }
}