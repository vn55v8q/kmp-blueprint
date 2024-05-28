package com.thoughtworks.multiplatform.blueprint.feature.account.di

import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ChangeNameViewModel
import feature.account.domain.ChangeName
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val changeNameModule = module {
    viewModel {
        ChangeNameViewModel(
            get(named(nameType)), get(), get()
        )
    }

    single {
        ChangeName(get())
    }
}