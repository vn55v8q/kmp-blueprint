package com.thoughtworks.multiplatform.blueprint.platform.designsystem.form

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun NameEditText(
    modifier: Modifier,
    value: String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = isEnabled,
        readOnly = false,
        textStyle = LocalTextStyle.current,
        label = {
            Text("Nombre")
        },
        placeholder = { Text("ej: Ivar Ragnarson") },
        leadingIcon = null,
        trailingIcon = null,
        prefix = null,
        suffix = null,
        supportingText = null,
        isError = isError,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        singleLine = false,
        interactionSource = null,
        shape = TextFieldDefaults.shape,
        colors = TextFieldDefaults.colors()
    )
}