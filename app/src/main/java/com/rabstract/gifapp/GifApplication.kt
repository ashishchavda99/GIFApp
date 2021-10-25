package com.rabstract.gifapp

import android.app.Application
import com.rabstract.gifapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GifApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@GifApplication)
            modules(listOf(appModule))
        }
    }
}