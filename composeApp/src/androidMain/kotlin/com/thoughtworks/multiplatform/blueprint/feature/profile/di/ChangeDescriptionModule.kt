package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeDescriptionViewModel
import feature.profile.domain.ChangeDescription
import feature.profile.domain.ValidateDescription
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changeDescriptionModule = module {
    viewModel {
        ChangeDescriptionViewModel(
            get(),
            get()
        )
    }

    single {
        ValidateDescription()
    }

    single {
        ChangeDescription(get())
    }
}