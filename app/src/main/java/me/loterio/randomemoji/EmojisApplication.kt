package me.loterio.randomemoji

import android.app.Application
import me.loterio.randomemoji.di.AndroidCoreModule
import me.loterio.randomemoji.di.AppComponent
import me.loterio.randomemoji.di.DaggerAppComponent
import me.loterio.randomemoji.di.ViewModelsModule
import me.loterio.randomemoji.repository.EmojisRepository

class EmojisApplication : Application() {


    lateinit var appComponent: AppComponent

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