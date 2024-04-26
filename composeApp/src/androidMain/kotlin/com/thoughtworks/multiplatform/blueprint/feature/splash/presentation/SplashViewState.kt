package com.thoughtworks.multiplatform.blueprint.feature.splash.presentation

import feature.onboarding.domain.IsNeedToShowOnboarding
import platform.version.domain.entity.VersionStatus

data class SplashViewState(
    val versionDevice: VersionStatus? = null,
    val isNeedToShowOnboarding: Boolean = false
)