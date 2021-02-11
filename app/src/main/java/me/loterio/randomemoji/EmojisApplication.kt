package me.loterio.randomemoji

import android.app.Application
import me.loterio.randomemoji.di.networkModule
import me.loterio.randomemoji.di.repositoryModule
import me.loterio.randomemoji.presentation.emojiListViewModel
import me.loterio.randomemoji.presentation.fragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmojisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EmojisApplication)
            modules(
                networkModule, repositoryModule, emojiListViewModel, fragmentModule
            )
        }
    }
}