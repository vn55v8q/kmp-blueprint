package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EmailEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleLargeText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme

@Composable
fun LoginEmailStepComponent(
    modifier: Modifier,
    email: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onEmailChange: (String) -> Unit,
    onConfirmEmail: () -> Unit
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        val message = remember {
            "Escribe tu contraseña para iniciar sesión en tu cuenta"
        }
        TitleLargeText(text = "Ya estás registrado/a")
        MediumSpacer()
        BodyMediumText(text =  "Escribe tu contraseña para iniciar sesión en tu cuenta")
        MediumSpacer()
        EmailEditText(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onEmailChange
        )
        MediumSpacer()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            SimpleButton(
                modifier = Modifier, text = "Continuar", isEnabled = true, onClick = onConfirmEmail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginStepComponentPreview() {
    AppTheme {
        LoginEmailStepComponent(modifier = Modifier,
            email = "harttyn.arce@gmail.com",
            isEnabled = true,
            isValid = true,
            onEmailChange = {},
            onConfirmEmail = {})
    }
}