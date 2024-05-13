package com.thoughtworks.multiplatform.blueprint.platform.designsystem.form

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier,
    title: String,
    showBackButton: Boolean,
    onClickBack: () -> Unit
) {
    TopAppBar(modifier = modifier, title = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "profile picture",
            )
            Text(title)
        }
    }, actions = {

    }, navigationIcon = {
        if (showBackButton) {
            IconButton(onClickBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "menu items"
                )
            }
        }
    })
}

@Preview
@Composable
private fun ToolbarPreview() {
    // TODO : ACA QUEDE
    AppTheme {
        Toolbar(modifier = Modifier.fillMaxWidth(),
            title = "Title",
            showBackButton = false,
            onClickBack = {})
    }
}