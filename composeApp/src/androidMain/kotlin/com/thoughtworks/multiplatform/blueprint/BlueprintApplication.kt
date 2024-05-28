package com.thoughtworks.multiplatform.blueprint

import android.app.Application
import com.thoughtworks.multiplatform.blueprint.feature.account.di.accountModule
import com.thoughtworks.multiplatform.blueprint.feature.account.di.avatarModule
import com.thoughtworks.multiplatform.blueprint.feature.account.di.changeNameModule
import com.thoughtworks.multiplatform.blueprint.feature.account.di.loginModule
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.di.onboardingModule
import com.thoughtworks.multiplatform.blueprint.feature.splash.di.splashModule
import com.thoughtworks.multiplatform.blueprint.platform.di.applicationModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BlueprintApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        startKoin {
            androidLogger()
            androidContext(this@BlueprintApplication)
            modules(
                applicationModule,
                onboardingModule,
                splashModule,
                accountModule,
                loginModule,
                avatarModule,
                changeNameModule
            )
        }
    }
}