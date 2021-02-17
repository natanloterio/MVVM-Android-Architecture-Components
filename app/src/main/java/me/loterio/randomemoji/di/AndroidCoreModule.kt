package me.loterio.randomemoji.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.EmojisApplication
import javax.inject.Singleton

@Module
class AndroidCoreModule() {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = EmojisApplication.application
}