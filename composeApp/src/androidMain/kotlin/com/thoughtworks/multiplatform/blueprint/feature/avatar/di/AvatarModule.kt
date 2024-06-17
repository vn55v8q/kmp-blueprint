package com.thoughtworks.multiplatform.blueprint.feature.avatar.di

import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ProfileViewModel
import com.thoughtworks.multiplatform.blueprint.feature.avatar.data.FirebaseAvatarClient
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.AvatarViewModel
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.UpdateImageViewModel
import feature.avatar.domain.AvatarClient
import feature.avatar.domain.GetAvatar
import feature.avatar.domain.UploadAvatar
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val avatarModule = module {
    single {
        GetAvatar(
            get()
        )
    }

    single<AvatarClient> {
        FirebaseAvatarClient(
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        AvatarViewModel(get())
    }

    viewModel {
        UpdateImageViewModel(get())
    }

    single {
        UploadAvatar(get())
    }
}