package com.thoughtworks.multiplatform.blueprint.feature.splash.ui

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashPanorama
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewState
import platform.version.domain.entity.VersionStatus

@Composable
fun SplashScreen(
    modifier: Modifier,
    state: SplashViewState,
    onNavigateToPanorama: (SplashPanorama) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column {
            state.versionDevice?.let { VersionDeviceMolecule(state = it) }
        }
    }
    LaunchedEffect(state.panorama) {
        if(state.panorama != SplashPanorama.DEFAULT){
            onNavigateToPanorama(state.panorama)
        }
    }
}

@Composable
fun VersionDeviceMolecule(modifier: Modifier = Modifier, state: VersionStatus) {
    Column(modifier = modifier) {
        Text("Version Info")
        Text("stable: ${state.stable}")
        Text("min: ${state.min}")
        Text("installed: ${state.installed}")
    }
}