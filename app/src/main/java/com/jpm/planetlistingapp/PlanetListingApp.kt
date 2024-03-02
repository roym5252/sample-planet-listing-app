package com.jpm.planetlistingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlanetListingApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}