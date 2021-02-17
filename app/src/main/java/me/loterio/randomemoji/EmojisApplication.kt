package me.loterio.randomemoji

import android.app.Application
import me.loterio.randomemoji.di.AndroidCoreModule
import me.loterio.randomemoji.di.AppComponent
import me.loterio.randomemoji.di.DaggerAppComponent

class EmojisApplication : Application() {


    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .androidCoreModule(AndroidCoreModule())
            .build()

        application = this
    }

    companion object  {
        lateinit var application: EmojisApplication
    }

}