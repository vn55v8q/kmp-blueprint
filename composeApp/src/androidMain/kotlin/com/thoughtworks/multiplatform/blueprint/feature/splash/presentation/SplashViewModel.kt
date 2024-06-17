package com.thoughtworks.multiplatform.blueprint.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.IsAutenticatedUser
import feature.onboarding.domain.IsNeedToShowOnboarding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import platform.version.domain.GetVersionStatus

class SplashViewModel(
    private val getVersionStatus: GetVersionStatus,
    private val isNeedToShowOnboarding: IsNeedToShowOnboarding,
    private val isAuthenticatedUser: IsAutenticatedUser
) : ViewModel() {

    private val mutableState = MutableStateFlow(SplashViewState())
    val state = mutableState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            val version = getVersionStatus.invoke()
            if (version.isForceUpdate.not()) {
                val isOnboarding = isNeedToShowOnboarding.invoke()
                if (isOnboarding) {
                    mutableState.value = state.value.copy(
                        versionDevice = getVersionStatus.invoke(),
                        panorama = SplashPanorama.ONBOARDING
                    )
                } else {
                    val newPanorama = if (isAuthenticatedUser.invoke())
                        SplashPanorama.HOME else
                        SplashPanorama.CREATE_ACCOUNT

                    mutableState.value = state.value.copy(
                        versionDevice = getVersionStatus.invoke(),
                        panorama = newPanorama
                    )
                }
            } else {
                mutableState.value = state.value.copy(
                    versionDevice = getVersionStatus.invoke(),
                    panorama = SplashPanorama.FORCE_UPDATE
                )
            }
        }
    }
}