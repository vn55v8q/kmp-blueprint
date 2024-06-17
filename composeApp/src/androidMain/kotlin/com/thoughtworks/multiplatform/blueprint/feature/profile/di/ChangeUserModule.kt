package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.account.di.nameType
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeUserViewModel
import feature.profile.domain.ChangeUser
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val changeUserModule = module {
    viewModel {
        ChangeUserViewModel(
            get(named(nameType)),
            get(),
            get()
        )
    }

    single {
        ChangeUser(get())
    }
}