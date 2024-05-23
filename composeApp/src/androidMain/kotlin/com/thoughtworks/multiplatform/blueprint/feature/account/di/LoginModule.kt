package com.thoughtworks.multiplatform.blueprint.feature.account.di

import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import platform.validators.domain.IsInvalidEmailForLogin

val loginModule = module {
    viewModel {
        LoginViewModel(get(), get(), get())
    }

    single {
        IsInvalidEmailForLogin(get())
    }
}