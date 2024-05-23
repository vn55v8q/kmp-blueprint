package com.thoughtworks.multiplatform.blueprint.feature.account.di

import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AvatarViewModel
import feature.account.domain.UploadImage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val avatarModule = module {
    viewModel {
        AvatarViewModel(get())
    }

    single {
        UploadImage(get())
    }
}