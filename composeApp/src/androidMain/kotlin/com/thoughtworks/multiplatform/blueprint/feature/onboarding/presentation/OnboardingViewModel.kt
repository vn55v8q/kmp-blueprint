package com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.onboarding.domain.FinishOnboarding
import feature.onboarding.domain.GetOnboarding
import feature.onboarding.domain.NotFoundOnboardingException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getOnboarding: GetOnboarding,
    private val finishOnboarding: FinishOnboarding
) : ViewModel() {
    private val mutableState = MutableStateFlow(OnboardingViewState())
    val state = mutableState.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            try {
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        onboarding = getOnboarding.invoke()
                    )
                }
            } catch (exception: NotFoundOnboardingException) {
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        onboarding = null,
                        errorOnboarding = exception.message.orEmpty()
                    )
                }
            }
        }
    }

    fun finish() {
        viewModelScope.launch {
            finishOnboarding.invoke()
            mutableState.update {
                it.copy(isFinish = true)
            }
        }
    }
}