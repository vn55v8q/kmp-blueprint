package com.thoughtworks.multiplatform.blueprint.platform.designsystem.form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier,
    title: String,
    titleAlign: TextAlign = TextAlign.Center,
    showBackButton: Boolean = false,
    onClickBack: () -> Unit = {}
) {
    TopAppBar(modifier = modifier, title = {
        if (titleAlign == TextAlign.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                TitleMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    textAlign = titleAlign,
                    showPadding = false
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                TitleMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = title,
                    textAlign = titleAlign,
                    showPadding = false
                )
            }
        }
    }, actions = {
        Spacer(modifier = Modifier.size(48.dp))
    }, navigationIcon = {
        Box(modifier = Modifier.size(48.dp)) {
            if (showBackButton) {
                IconButton(onClickBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "menu items"
                    )
                }
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
            titleAlign = TextAlign.Center,
            showBackButton = false,
            onClickBack = {})
    }
}