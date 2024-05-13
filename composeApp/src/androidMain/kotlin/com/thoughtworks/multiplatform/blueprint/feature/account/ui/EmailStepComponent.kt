package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EmailEditText

@Composable
fun EmailStepComponent(
    modifier: Modifier,
    email: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onEmailChange: (String) -> Unit,
    onConfirmEmail: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailEditText(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onEmailChange
        )

        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = true,
            onClick = onConfirmEmail
        )
    }
}