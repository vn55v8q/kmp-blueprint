package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.R
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.button.SimpleButton
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.form.Toolbar
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.BodySmallText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import platform.log.Log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    modifier: Modifier, onClickLogin: () -> Unit, onClickRegister: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val coroutine = rememberCoroutineScope()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
        confirmValueChange = {
            false
        }
    )
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        openBottomSheet = true
    }
    Scaffold(topBar = {
        Toolbar(
            modifier = Modifier.fillMaxWidth(), title = "Perfil"
        )
    }, content = { padding ->

        Box(modifier = modifier.padding(padding), contentAlignment = Alignment.BottomCenter) {

            Image(
                modifier = Modifier.padding(horizontal = 16.dp),
                painter = painterResource(id = R.drawable.thoughtworks_logo),
                contentDescription = "Logo"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = ""
                    )
                    BodySmallText(text = "Iniciar sesión en cuenta existente")
                    SimpleButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Inicio de sesión",
                        isEnabled = true,
                        onClick = {
                            Log.d("AccountScreen", "OnClick")
                            coroutine.launch {
                                openBottomSheet = true
                                bottomSheetState.show()
                            }
                        }
                    )
                }
            }

            if (openBottomSheet) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    ModalBottomSheet(
                        modifier = Modifier.fillMaxSize(),
                        onDismissRequest = {},
                        sheetState = bottomSheetState,
                        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = false)
                    ) {
                        AccountContent(
                            modifier = Modifier.fillMaxSize(),
                            onClickLogin = {
                                coroutine.launch {
                                    bottomSheetState.hide()
                                    onClickLogin()
                                }
                            }, onClickRegister = {
                                coroutine.launch {
                                    bottomSheetState.hide()
                                    onClickRegister()
                                }
                            }, onCloseModal = {
                                coroutine.launch {
                                    bottomSheetState.hide()
                                    openBottomSheet = false
                                }
                            })
                    }
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
private fun AccountScreenPreview() {
    AppTheme {
        AccountScreen(modifier = Modifier.fillMaxSize(), onClickLogin = { }, onClickRegister = {})
    }
}