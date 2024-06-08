package com.thoughtworks.multiplatform.blueprint.feature.avatar.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.UpdateImageState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.SnackbarMessage
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.SnackbarVisualsWithError
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.bar.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.LoadingButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import com.thoughtworks.multiplatform.blueprint.platform.multimedia.ImageInfo
import feature.account.domain.TypeImage
import kotlinx.coroutines.launch

@Composable
fun ImageSelectScreen(
    modifier: Modifier,
    state: UpdateImageState,
    onProcessImage: (String, TypeImage) -> Unit,
    onBackClick: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                uri = it
            })

    LaunchedEffect(key1 = Unit) {
        singlePhotoPicker.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    LaunchedEffect(key1 = state.message) {
        if (state.message.isNotEmpty()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    SnackbarVisualsWithError(
                        state.message,
                        isError = false
                    )
                )
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                SnackbarMessage(
                    text = data.visuals.message,
                    textButton = data.visuals.actionLabel.orEmpty()
                ) {
                    data.dismiss()
                }
            }
        },
        topBar = {
            Toolbar(
                modifier = Modifier.fillMaxWidth(),
                title = state.name,
                showBackButton = true,
                onClickBack = onBackClick
            )
        }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Button(
                enabled = state.isLoading.not(),
                onClick = {
                singlePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )

            }) {
                TitleMediumText("Seleccionar Imagen", color = Color.White)
            }

            AsyncImage(modifier = Modifier.size(248.dp), model = uri, contentDescription = null)

            LoadingButton(
                modifier = Modifier,
                text = "Guardar",
                isLoading = state.isLoading,
                isEnabled = state.isLoading.not()
            ) {
                val typeImage = ImageInfo.getTypeImage(context, uri)
                onProcessImage(uri.toString(), typeImage)
            }
        }
    }
}
