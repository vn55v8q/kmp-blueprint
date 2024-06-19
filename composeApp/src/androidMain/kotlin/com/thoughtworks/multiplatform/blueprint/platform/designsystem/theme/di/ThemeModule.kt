package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.di

import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.presentation.ThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import platform.theme.data.DataThemeClient
import platform.theme.domain.GetTheme
import platform.theme.domain.SaveTheme
import platform.theme.domain.ThemeClient

val themeModule = module {
    single<ThemeClient> {
        DataThemeClient(get())
    }

    single {
        SaveTheme(get())
    }

    single {
        GetTheme(get())
    }

    viewModel {
        ThemeViewModel(get(), get())
    }
}