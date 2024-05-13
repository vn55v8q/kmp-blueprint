package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EmailEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.NameEditText

@Composable
fun NameStepComponent(
    modifier: Modifier,
    name: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onNameChange: (String) -> Unit,
    onConfirmName: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        NameEditText(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onNameChange
        )

        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = isEnabled,
            onClick = onConfirmName
        )
    }
}