package com.thoughtworks.multiplatform.blueprint.feature.account.di

import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseNameBlackList
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.validators.data.LocalNameBlackListCache
import platform.validators.domain.BlackListClient
import platform.validators.domain.BlackListCacheClient
import platform.validators.domain.IsInvalidName
import platform.validators.domain.UpdateLocalString

const val nameType = "name"

val accountModule = module {

    viewModel {
        AccountViewModel(get(named(nameType)), get())
    }
    single<BlackListClient<String>>(named(nameType)) {
        FirebaseNameBlackList(firestoreDatabase = get())
    }

    single<BlackListCacheClient<String>>(named(nameType)) {
        LocalNameBlackListCache()
    }

    single<UpdateLocalString>(named(nameType)) {
        UpdateLocalString(
            get(named(nameType)),
            get(named(nameType))
        )
    }

    single {
        IsInvalidName(
            local = get(named(nameType))
        )
    }
}