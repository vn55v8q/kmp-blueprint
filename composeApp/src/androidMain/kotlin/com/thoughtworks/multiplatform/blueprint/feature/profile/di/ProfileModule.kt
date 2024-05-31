package com.thoughtworks.multiplatform.blueprint.feature.profile.di

import com.thoughtworks.multiplatform.blueprint.feature.profile.data.FirebaseProfileClient
import feature.profile.domain.GetProfile
import feature.profile.domain.ProfileClient
import org.koin.dsl.module

val profileModule = module {
    single<ProfileClient> {
        FirebaseProfileClient(
            get(),
            get(),
            get()
        )
    }

    single {
        GetProfile(get())
    }
}