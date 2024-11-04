package com.demo.vdemotask

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
public class MainApplication : Application(){


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}