package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.Birthday
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.BirthdayPickerButton
import platform.log.Log

@Composable
fun AgeStepComponent(
    modifier: Modifier, onConfirmBirthday: (Birthday) -> Unit
) {
    val ageSelected = remember { mutableStateOf(Birthday.empty()) }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        BirthdayPickerButton(
            modifier = Modifier.fillMaxWidth(), currentBirthday = ageSelected.value
        ) { newBirthday ->
            ageSelected.value = newBirthday
        }

        SimpleButton(modifier = Modifier,
            text = "Crear cuenta",
            isEnabled = ageSelected.value.month > 0,
            onClick = {
                onConfirmBirthday(ageSelected.value)
            })
    }
}