package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.account.di.nameType
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeNameViewModel
import feature.profile.domain.ChangeName
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