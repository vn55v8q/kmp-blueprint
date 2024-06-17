package com.thoughtworks.multiplatform.blueprint.feature.splash.presentation

import platform.version.domain.entity.VersionStatus

data class SplashViewState(
    val versionDevice: VersionStatus? = null,
    val panorama: SplashPanorama = SplashPanorama.DEFAULT,
)

enum class SplashPanorama {
    DEFAULT, ONBOARDING, HOME, CREATE_ACCOUNT, ROOTED_DECIVE, FORCE_UPDATE, NOTIFICATION_INFO
}