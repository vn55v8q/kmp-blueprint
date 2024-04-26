package com.thoughtworks.multiplatform.blueprint.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.onboarding.domain.IsNeedToShowOnboarding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import platform.version.domain.GetVersionStatus

class SplashViewModel(
    private val getVersionStatus: GetVersionStatus,
    private val isNeedToShowOnboarding: IsNeedToShowOnboarding
) : ViewModel() {

    private val mutableState = MutableStateFlow(SplashViewState())
    val state = mutableState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            mutableState.value = state.value.copy(
                versionDevice = getVersionStatus.invoke(),
                isNeedToShowOnboarding = isNeedToShowOnboarding.invoke()
            )
        }
    }
}