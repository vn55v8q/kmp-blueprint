plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.googleServices)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.shared)
        }
    }
}

android {
    namespace = "com.thoughtworks.multiplatform.blueprint"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.thoughtworks.multiplatform.blueprint"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 4
        versionName = "0.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(libs.androidx.core.ktx)
        implementation(platform(libs.koin.bom))
        implementation(libs.koin.core)
        implementation(libs.koin.android)
        implementation(libs.koin.android.compose)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.coil.compose)
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.storage)
        implementation(libs.firebase.auth)
        implementation(libs.firebase.firestore.ktx)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.material.icons.extended)
    }

    flavorDimensions += "flavor"
    productFlavors {
        create("dev") {
            dimension = "flavor"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Blueprint-Dev")
        }
        create("qa") {
            dimension = "flavor"
            applicationIdSuffix = ".qa"
            resValue("string", "app_name", "Blueprint-QA")
        }
        create("prod") {
            dimension = "flavor"
            resValue("string", "app_name", "Blueprint")
        }
    }
}

