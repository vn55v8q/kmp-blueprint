package com.thoughtworks.multiplatform.blueprint.feature.account.di

import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseDomainBlackList
import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseDotComBlackList
import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseNameBlackList
import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseUserClient
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import feature.account.domain.GetAccount
import feature.account.domain.LoginUser
import feature.account.domain.RegisterUser
import feature.account.domain.UserClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.validators.data.LocalDomainBlackListCache
import platform.validators.data.LocalDotComBlackListCache
import platform.validators.data.LocalNameBlackListCache
import platform.validators.domain.BlackListCacheClient
import platform.validators.domain.BlackListClient
import platform.validators.domain.IsInvalidEmailForRegister
import platform.validators.domain.IsInvalidEmailDomain
import platform.validators.domain.IsInvalidEmailDotCom
import platform.validators.domain.IsInvalidName
import platform.validators.domain.PasswordValidator
import platform.validators.domain.ParseEmailToEntity
import platform.validators.domain.UpdateLocalString

const val nameType = "name"
const val domainType = "domain"
const val dotComType = "dot"

val accountModule = module {

    viewModel {
        AccountViewModel(
            get(named(nameType)),
            get(named(domainType)),
            get(named(dotComType)),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    // Black List Name
    single<BlackListClient<String>>(named(nameType)) {
        FirebaseNameBlackList(firestoreDatabase = get())
    }

    single<BlackListCacheClient<String>>(named(nameType)) {
        LocalNameBlackListCache()
    }

    single(named(nameType)) {
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
    // End Black List Name

    // Black List Email
    single {
        IsInvalidEmailForRegister(
            parseEmailToEntity = get(),
            isInvalidName = get(),
            isInvalidDomain = get(),
            isInvalidDotCom = get()
        )
    }

    single {
        ParseEmailToEntity()
    }

    // Domain
    single(named(domainType)) {
        UpdateLocalString(
            get(named(domainType)), get(named(domainType))
        )
    }

    single<BlackListClient<String>>(named(domainType)) {
        FirebaseDomainBlackList(firestoreDatabase = get())
    }

    single<BlackListCacheClient<String>>(named(domainType)) {
        LocalDomainBlackListCache()
    }

    single {
        IsInvalidEmailDomain(
            emailDomainUpdateLocalString = get(named(domainType)),
            local = get(named(domainType))
        )
    }
    // End Domain

    // Dot com
    single(named(dotComType)) {
        UpdateLocalString(
            get(named(dotComType)), get(named(dotComType))
        )
    }

    single<BlackListClient<String>>(named(dotComType)) {
        FirebaseDotComBlackList(firestoreDatabase = get())
    }

    single<BlackListCacheClient<String>>(named(dotComType)) {
        LocalDotComBlackListCache()
    }

    single {
        IsInvalidEmailDotCom(
            dotComUpdateLocalString = get(named(dotComType)),
            local = get(named(dotComType))
        )
    }
    // End Dot Com
    single {
        PasswordValidator()
    }

    single {
        RegisterUser(get())
    }

    single {
        LoginUser(get())
    }

    single<UserClient> {
        FirebaseUserClient(get(), get(), get(), get())
    }

    single {
        GetAccount(get())
    }
}