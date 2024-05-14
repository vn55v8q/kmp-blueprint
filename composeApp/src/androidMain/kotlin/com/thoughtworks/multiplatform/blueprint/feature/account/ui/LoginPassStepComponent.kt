package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.PaswordEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleLargeText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleSmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme

@Composable
fun LoginPassStepComponent(
    modifier: Modifier,
    password: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onPasswordChange: (String) -> Unit,
    onConfirmPassword: () -> Unit,
    onClickPasswordRecovery: () -> Unit
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        TitleLargeText(text = "Introduce la contraseña")
        MediumSpacer()
        MediumSpacer()
        PaswordEditText(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onPasswordChange
        )
        MediumSpacer()
        TitleSmallText(
            modifier = Modifier.clickable {
                onClickPasswordRecovery()
            },
            text = "¿Olvidaste la contraseña?"
        )
        MediumSpacer()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            SimpleButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Iniciar sesión",
                isEnabled = true,
                onClick = onConfirmPassword
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPassStepComponentPreview() {
    AppTheme {
        LoginPassStepComponent(modifier = Modifier,
            password = "harttyn.arce@gmail.com",
            isEnabled = true,
            isValid = true,
            onPasswordChange = {},
            onConfirmPassword = {},
            onClickPasswordRecovery = {}
        )
    }
}