package com.thoughtworks.multiplatform.blueprint.feature.home.di

import com.thoughtworks.multiplatform.blueprint.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(get())
    }
}