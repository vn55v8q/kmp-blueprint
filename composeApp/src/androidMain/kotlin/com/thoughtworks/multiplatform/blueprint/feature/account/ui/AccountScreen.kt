package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountState

@Composable
fun AccountScreen(
    modifier: Modifier,
    state: AccountState,
    onChangeName: (String) -> Unit
) {
    Column {
        Text(state.toString())
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.name,
            enabled = true,//state.isValidName,
            onValueChange = onChangeName,
            singleLine = true,
            placeholder = {
                Text(text = "Ingresa un nombre")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            /*colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )*/
        )
    }
}