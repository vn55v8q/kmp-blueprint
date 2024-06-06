package com.thoughtworks.multiplatform.blueprint.platform.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.russhwolf.settings.Settings
import org.koin.dsl.module
import platform.cache.data.DataCacheReference
import platform.cache.domain.CacheReference

val applicationModule = module {
    single {
        FirebaseFirestore.getInstance()
    }

    single<Settings> {
        Settings()
    }

    single {
        Firebase.storage
    }

    single {
        FirebaseAuth.getInstance()
    }

    single<CacheReference> {
        DataCacheReference(get())
    }
}