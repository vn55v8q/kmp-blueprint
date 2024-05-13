package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EmailEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.NameEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.UserEditText

@Composable
fun UserStepComponent(
    modifier: Modifier,
    user: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onUserChange: (String) -> Unit,
    onConfirmUser: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        UserEditText(
            modifier = Modifier.fillMaxWidth(),
            value = user,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onUserChange
        )

        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = isEnabled,
            onClick = onConfirmUser
        )
    }
}