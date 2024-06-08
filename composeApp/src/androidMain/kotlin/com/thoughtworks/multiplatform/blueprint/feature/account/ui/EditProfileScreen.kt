package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.image.CircularWebImage
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.panel.FormRow
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import platform.log.Log

@Composable
fun EditProfileScreen(
    modifier: Modifier,
    state: ProfileState,
    onBackClick: () -> Unit,
    onClickChangeImage: () -> Unit,
    onClickChangeName: () -> Unit,
    onClickChangeUser: () -> Unit,
    onClickChangePronoun: () -> Unit,
    onClickChangeDescription: () -> Unit,
) {
    Log.d("ProfileScene", "EditProfileScreen name: ${state.name}")
    Scaffold(
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                title = state.name,
                showBackButton = true,
                onClickBack = onBackClick
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularWebImage(
                    modifier = Modifier
                        .size(120.dp),
                    url = state.urlImage,
                    onClick = onClickChangeImage
                )
                BodySmallText(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Cambiar foto"
                )
            }
            MediumSpacer()
            FormRow(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                leftText = "Nombre",
                rightText = state.name,
                rightIcon = Icons.Rounded.ChevronRight,
                onClick = onClickChangeName
            )
            FormRow(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                leftText = "Nombre de usuario",
                rightText = state.user,
                rightIcon = Icons.Rounded.ChevronRight,
                onClick = onClickChangeUser
            )
            FormRow(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                leftText = "Pronombres",
                rightText = state.pronoun,
                rightIcon = Icons.Rounded.ChevronRight,
                onClick = onClickChangePronoun
            )
            FormRow(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                leftText = "Descripción",
                rightText = state.shortDescription,
                rightIcon = Icons.Rounded.ChevronRight,
                onClick = onClickChangeDescription
            )
            HorizontalDivider()
        }
    }
}
