package com.thoughtworks.multiplatform.blueprint.feature.splash.presentation

import platform.version.domain.entity.VersionStatus

data class SplashViewState(
    val versionDevice: VersionStatus? = null
)