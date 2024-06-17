package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangePronounViewModel
import feature.profile.domain.ChangePronoun
import feature.profile.domain.ValidatePronoun
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changePronounModule = module {
    viewModel {
        ChangePronounViewModel(
            get(),
            get()
        )
    }

    single {
        ValidatePronoun()
    }

    single {
        ChangePronoun(get())
    }
}