package com.mehmetkaya.scorpion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class ScorpionApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
