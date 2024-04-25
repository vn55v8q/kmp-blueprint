package com.thoughtworks.multiplatform.blueprint.feature.onboarding.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.storage
import com.russhwolf.settings.Settings
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.data.FirebaseClientOnboarding
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import feature.onboarding.data.DefaultClientOnboarding
import feature.onboarding.data.RemoteClientOnboarding
import feature.onboarding.data.SharedOnboardingStore
import feature.onboarding.domain.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val onboardingModule = module {

    viewModel {
        OnboardingViewModel(get(), get())
    }

    single<Settings> {
        Settings()
    }

    single<OnboardingStorage> {
        SharedOnboardingStore(get())
    }

    single {
        FinishOnboarding(get())
    }

    single<ClientOnboarding>(named("remote")) {
        FirebaseClientOnboarding(Firebase.storage)
    }

    single<ClientOnboarding>(named("default")) {
        DefaultClientOnboarding()
    }

    single {
        GetOnboarding(
            remoteClient = get(qualifier = named("remote")),
            defaultClient = get(qualifier = named("default"))
        )
    }

    single {
        FinishOnboarding(get())
    }

    single {
        IsNeedToShowOnboarding(get())
    }
}