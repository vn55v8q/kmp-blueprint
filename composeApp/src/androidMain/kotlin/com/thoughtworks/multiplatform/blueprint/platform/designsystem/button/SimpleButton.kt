package com.thoughtworks.multiplatform.blueprint.platform.designsystem.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thoughtworks.multiplatform.blueprint.R
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText

@Composable
fun SimpleButton(
    modifier: Modifier,
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(),
        elevation = ButtonDefaults.buttonElevation(),
        border = null,
        contentPadding = ButtonDefaults.ContentPadding,
        interactionSource = null
    ) {
        Text(text)
    }
}

@Composable
fun SignInEmailMethodButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(modifier = modifier, onClick = onClick, enabled = isEnabled) {
        Icon(
            painter = painterResource(id =  R.drawable.ic_person), contentDescription = "login"
        )
        BodyMediumText(text = text)
    }
}