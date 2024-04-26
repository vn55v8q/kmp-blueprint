package com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation

import feature.onboarding.domain.Onboarding

data class OnboardingViewState(
    val isLoading: Boolean = true,
    val onboarding: Onboarding? = null,
    val isFinish: Boolean = false,
    val errorOnboarding: String? = null
)