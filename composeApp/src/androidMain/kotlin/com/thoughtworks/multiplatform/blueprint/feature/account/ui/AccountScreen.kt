package com.thoughtworks.multiplatform.blueprint.feature.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.R
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    modifier: Modifier, onClickLogin: () -> Unit, onClickRegister: () -> Unit
) {
    val coroutine = rememberCoroutineScope()
    val openBottomSheet by rememberSaveable { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState(confirmValueChange = {
        false
    })
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.thoughtworks_logo),
            contentDescription = "Logo"
        )

        if (openBottomSheet) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxSize(),
                    onDismissRequest = {},
                    sheetState = bottomSheetState,
                    properties = ModalBottomSheetProperties(shouldDismissOnBackPress = false)
                ) {
                    AccountContent(
                        onClickLogin = {
                            coroutine.launch {
                                bottomSheetState.hide()
                                onClickLogin()
                            }
                        },
                        onClickRegister = {
                            coroutine.launch {
                                bottomSheetState.hide()
                                onClickRegister()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountScreenPreview() {
    AppTheme {
        AccountScreen(modifier = Modifier.fillMaxSize(), onClickLogin = { }, onClickRegister = {})
    }
}