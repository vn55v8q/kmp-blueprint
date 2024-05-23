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
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.UserEditText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText

@Composable
fun UserStepComponent(
    modifier: Modifier,
    user: String,
    errorMessage: String,
    isEnabled: Boolean = true,
    isValid: Boolean,
    onUserChange: (String) -> Unit,
    onConfirmUser: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "Crea un apodo"
        )
        BodySmallText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "Puede ser cualquiera y se podrá cambiar más tarde.",
            showPadding = false
        )
        UserEditText(
            modifier = Modifier.fillMaxWidth(),
            value = user,
            isEnabled = isEnabled,
            isError = isValid.not(),
            onValueChange = onUserChange
        )
        if (isValid.not() && errorMessage.isNotEmpty()){
            BodySmallText(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        SimpleButton(
            modifier = Modifier,
            text = "Continuar",
            isEnabled = isEnabled,
            onClick = onConfirmUser
        )
    }
}