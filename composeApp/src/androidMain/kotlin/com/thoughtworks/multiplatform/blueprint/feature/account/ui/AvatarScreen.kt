package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.image.CircularWebImage
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import java.util.UUID

@Composable
fun AvatarScreen(
    modifier: Modifier, state: ProfileState,
    onBackClick: () -> Unit,
    onClickEditProfile: () -> Unit,
    onClickChangeImage: () -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                title = state.name,
                showBackButton = false,
                onClickBack = onBackClick
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularWebImage(
                modifier = Modifier
                    .size(120.dp),
                url = state.urlImage,
                onClick = onClickChangeImage
            )

            TitleMediumText(text = state.user)

            SimpleButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Editar Perfil",
                isEnabled = true,
                onClick = onClickEditProfile
            )
        }
    }
}
