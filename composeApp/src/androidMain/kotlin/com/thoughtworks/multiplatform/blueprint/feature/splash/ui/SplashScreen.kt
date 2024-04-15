package com.thoughtworks.multiplatform.blueprint.feature.splash.ui

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewState
import platform.version.domain.entity.VersionStatus

@Composable
fun SplashScreen(
    modifier: Modifier,
    state: SplashViewState
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column {
            state.versionDevice?.let { VersionDeviceMolecule(state = it) }
        }
    }
}

@Composable
fun VersionDeviceMolecule(modifier: Modifier = Modifier, state: VersionStatus) {
    Column {
        Text("Version Info")
        Text("stable: ${state.stable}")
        Text("min: ${state.min}")
        Text("installed: ${state.installed}")
    }
}