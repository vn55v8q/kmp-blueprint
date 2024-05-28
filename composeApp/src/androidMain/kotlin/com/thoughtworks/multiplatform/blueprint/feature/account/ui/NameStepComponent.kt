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
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.NameEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText

@Composable
fun NameStepComponent(
    modifier: Modifier,
    title: String = "Ingresa tu nombre",
    description: String = "La información personal puede ser modificada desde la opción Editar Perfil.",
    buttonText: String = "Continuar",
    name: String,
    errorMessage: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onNameChange: (String) -> Unit,
    onConfirmName: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = title
        )
        BodySmallText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = description,
            showPadding = false
        )
        NameEditText(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onNameChange
        )
        if (isValid.not() && errorMessage.isNotEmpty()) {
            BodySmallText(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        SimpleButton(
            modifier = Modifier,
            text = buttonText,
            isEnabled = isEnabled,
            onClick = onConfirmName
        )
    }
}