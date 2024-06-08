package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangePronounViewModel
import feature.profile.domain.ChangePronoun
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changePronounModule = module {
    viewModel {
        ChangePronounViewModel(
            get()
        )
    }

    single {
        ChangePronoun(get())
    }
}