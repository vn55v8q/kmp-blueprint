package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.PaswordEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleSmallText
import platform.validators.domain.PasswordStrength

@Composable
fun PasswordStepComponent(
    modifier: Modifier,
    password: String,
    passwordStrength: PasswordStrength,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onPasswordChange: (String) -> Unit,
    onConfirmPassword: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "Crea una contraseña"
        )
        PaswordEditText(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onPasswordChange
        )
        Column {
            TitleSmallText(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = "Tu contraseña debe tener al menos:"
            )
            BodySmallText(text = "8 caracteres (20 máx.)")
            BodySmallText(text = "1 letra y 1 numero")
            BodySmallText(text = "seguridad de la contraseña: ${passwordStrength.security}")
        }
        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = isEnabled,
            onClick = onConfirmPassword
        )
    }
}