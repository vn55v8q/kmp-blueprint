package com.thoughtworks.multiplatform.blueprint.platform.designsystem.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText

@Composable
fun PermissionDialog(
    onDissmissRequest : () -> Unit,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDissmissRequest,
        title = {
            TitleMediumText(text = "Crear Imagen de Perfil")
        },
        text = {
            BodyMediumText(text = "Para crear una imagen de perfil de usuario, necesitamos permisos a tu galeria de fotos")
        },
        confirmButton = {
            TextButton(
                onClick = onOkClick
            ) {
                BodyMediumText("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancelClick
            ) {
                BodyMediumText("Cancelar")
            }
        }
    )
}