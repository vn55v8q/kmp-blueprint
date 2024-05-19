package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EmailEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText

@Composable
fun EmailStepComponent(
    modifier: Modifier,
    email: String,
    errorMessage: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onEmailChange: (String) -> Unit,
    onConfirmEmail: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "Correo electronico"
        )
        EmailEditText(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            isEnabled = isEnabled,
            isError = isValid.not(),
            errorMessage = "",
            onValueChange = onEmailChange
        )
        if (isValid.not() && errorMessage.isNotEmpty()){
            BodySmallText(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        BodySmallText(text = "* La dirección de correo electronico puede usarse para comprar nuevo contenido y almacenar tu pogreso, junto con " +
                "la obtención de ofertas especiales.")

        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = true,
            onClick = onConfirmEmail
        )
    }
}