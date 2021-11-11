plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.mehmetkaya.scorpion"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionName = Config.versionName
        versionCode = Config.versionCode
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    // Kotlin
    implementation(Deps.kotlin)

    // Ktx
    implementation(Deps.coreKtx)
    implementation(Deps.activityKtx)
    implementation(Deps.viewModelKtx)
    implementation(Deps.liveDataKtx)

    // Support Libraries
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.swipeRefreshLayout)

    // Hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    // Coroutines
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
}
