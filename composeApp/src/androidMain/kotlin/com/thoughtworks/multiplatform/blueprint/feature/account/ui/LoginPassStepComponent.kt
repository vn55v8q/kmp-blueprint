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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.LoadingButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.PasswordEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleLargeText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleSmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme

@Composable
fun LoginPassStepComponent(
    modifier: Modifier,
    password: String,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    isValid: Boolean,
    errorMessage: String,
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
        PasswordEditText(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onPasswordChange
        )
        if (isValid.not() && errorMessage.isNotEmpty()){
            BodySmallText(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        MediumSpacer()
        TitleSmallText(
            modifier = Modifier.clickable {
                onClickPasswordRecovery()
            },
            text = "¿Olvidaste la contraseña?"
        )
        MediumSpacer()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            LoadingButton(
                modifier = Modifier,
                text = "Iniciar sesión",
                isLoading = isLoading,
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
            errorMessage = "This is a error",
            isEnabled = true,
            isValid = true,
            onPasswordChange = {},
            onConfirmPassword = {},
            onClickPasswordRecovery = {}
        )
    }
}