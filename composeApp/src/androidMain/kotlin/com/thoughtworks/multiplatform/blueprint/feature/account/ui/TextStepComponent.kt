package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.LoadingButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.EditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText

@Composable
fun TextStepComponent(
    modifier: Modifier,
    isLandscape: Boolean,
    isLoading: Boolean,
    title: String = "Ingrese Descripción",
    label: String = "Description",
    description: String = "Los datos son privados y almacenados de forma segura.",
    buttonText: String = "Guardar",
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
        Row(modifier = Modifier.fillMaxWidth()) {
            val weight = if (isLandscape) 0.8f else 1f
            EditText(
                modifier = Modifier.weight(weight),
                value = name,
                placeHolder = title,
                label = label,
                isEnabled = isEnabled,
                isError = isValid.not(),
                onValueChange = onNameChange
            )
            if (isLandscape) {
                Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.Center) {
                    LoadingButton(
                        modifier = Modifier,
                        isLoading = isLoading,
                        text = buttonText,
                        isEnabled = isValid,
                        onClick = onConfirmName
                    )
                }
            }
        }
        if (isValid.not() && errorMessage.isNotEmpty()) {
            BodySmallText(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        if (isLandscape.not()) {
            LoadingButton(
                modifier = Modifier,
                isLoading = isLoading,
                text = buttonText,
                isEnabled = isValid,
                onClick = onConfirmName
            )
        }
    }
}