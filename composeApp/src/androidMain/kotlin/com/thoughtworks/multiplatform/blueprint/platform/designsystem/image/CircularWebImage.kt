package com.thoughtworks.multiplatform.blueprint.platform.designsystem.image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.onPrimaryContainerLight
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.primaryLight
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.surfaceBrightLight
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.surfaceDimLight
import platform.log.Log

@Composable
fun CircularWebImage(
    modifier: Modifier,
    url: String,
    onClick: () -> Unit
) {
    if (url.isEmpty()) {
        CircularSolid(modifier.fillMaxSize(), onClick)
    } else {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable(onClick = onClick)
                .border(5.dp, Color.Gray, CircleShape)
        )
    }
}

@Composable
fun CircularSolid(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Avatar Defaut",
            modifier = Modifier
                .fillMaxSize()
                .background(surfaceDimLight, CircleShape)
                .clip(CircleShape)
                .clickable(onClick = onClick)
                .padding(2.dp),
            tint = surfaceBrightLight
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Avatar Defaut",
                modifier = Modifier
                    .size(36.dp)
                    .background(onPrimaryContainerLight, CircleShape)
                    .padding(2.dp),
                tint = primaryLight
            )
        }
    }
}