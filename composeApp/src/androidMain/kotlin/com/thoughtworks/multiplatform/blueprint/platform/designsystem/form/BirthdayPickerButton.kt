package com.thoughtworks.multiplatform.blueprint.platform.designsystem.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import platform.log.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayPickerButton(
    modifier: Modifier, currentBirthday: Birthday, onClick: (Birthday) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FilledIconButton(modifier = Modifier.fillMaxWidth(), onClick = {
            openDialog.value = true
        }) {
            if (currentBirthday.month > 0) {
                Text(currentBirthday.formatted)
            } else {
                Icon(Icons.Outlined.CalendarMonth, contentDescription = "Localized description")
            }
        }

        if (openDialog.value) {
            val datePickerState = rememberDatePickerState()
            val confirmEnabled = remember {
                derivedStateOf { datePickerState.selectedDateMillis != null }
            }
            DatePickerDialog(onDismissRequest = {
                openDialog.value = false
            }, confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onClick(
                            Birthday.longToBirthday(
                                datePickerState.selectedDateMillis?.plus(1) ?: 0
                            )
                        )
                    }, enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            }, dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }) {
                    Text("Cancel")
                }
            }) {
                DatePicker(state = datePickerState)
            }
        }

    }
}

data class Birthday(
    val day: Int, val month: Int, val year: Int, val age: Int, val formatted: String
) {
    companion object {
        fun empty() = Birthday(0, 0, 0, 0, "")
        fun longToBirthday(time: Long): Birthday {
            // TODO : Se detecta issue con componente al seleccionar el 01 de Mayo del 2024, en el componente se muestra 31 de Abril de 2024
            // value: 1714521600000
            // GMT: Wednesday, May 1, 2024 12:00:00 AM
            // Your time zone: Tuesday, April 30, 2024 8:00:00 PM GMT-04:00
            // por lo anterior se saca la edad del registro inicial.
            // fuente: https://www.epochconverter.com/
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val dateString = formatter.format(Date(time))
            val values = dateString.split("/")
            val day = values[0].toInt()
            val month = values[1].toInt()
            val year = values[2].toInt()
            val currentCalendar = Calendar.getInstance()
            val currentYear = currentCalendar.get(Calendar.YEAR)
            val age = currentYear - year
            val dayFormatter = if (day >= 10) "$day" else "0$day"
            val monthFormatter = if (month >= 10) "$month" else "0$month"
            val birthdayFormatter = "$dayFormatter/$monthFormatter/$year"
            return Birthday(day, month, year, age, birthdayFormatter)
        }
    }
}