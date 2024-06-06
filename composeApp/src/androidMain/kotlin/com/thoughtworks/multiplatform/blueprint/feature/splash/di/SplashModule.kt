package com.thoughtworks.multiplatform.blueprint.feature.splash.di

import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.platform.version.domain.AndroidDeviceVersion
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import platform.version.data.SharedRemoteVersion
import platform.version.domain.DeviceVersion
import platform.version.domain.GetVersionStatus
import platform.version.domain.RemoteVersion

val splashModule = module {
    single<DeviceVersion> {
        AndroidDeviceVersion()
    }

    single<RemoteVersion> {
        SharedRemoteVersion()
    }

    single {
        GetVersionStatus(get(), get())
    }
    
    viewModel {
        SplashViewModel(get(), get(), get())
    }
}