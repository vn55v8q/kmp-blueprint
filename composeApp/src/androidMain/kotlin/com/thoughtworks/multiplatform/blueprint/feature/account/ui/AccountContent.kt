package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.CloseIconButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SignInEmailMethodButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.SmallSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodyMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.primaryLight

@Composable
fun AccountContent(
    modifier : Modifier,
    onCloseModal: () -> Unit, onClickLogin: () -> Unit, onClickRegister: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)

            , contentAlignment = Alignment.TopEnd) {
            TitleMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 46.dp),
                text = "Inicio de sesión",
                textAlign = TextAlign.Center
            )
            CloseIconButton(
                onClick = onCloseModal
            )
        }

        BodyMediumText(
            text = "Gestiona tu cuenta, lee notificaciones, utiliza todas las funcionalidades de nuetra app.",
            showPadding = true
        )
        SignInEmailMethodButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Usar correo electronico",
            onClick = onClickLogin
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickRegister()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BodyMediumText(text = "¿No tienes cuenta?")
            SmallSpacer()
            TitleMediumText(text = "Registrate", color = primaryLight)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountContentPreview() {
    AppTheme {
        AccountContent(modifier = Modifier.fillMaxSize(),{}, {}) {}
    }
}