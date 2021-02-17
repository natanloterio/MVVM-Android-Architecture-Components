package me.loterio.randomemoji.di

import dagger.Component
import me.loterio.randomemoji.presentation.FragmenEmojiList
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidCoreModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelsModule::class
])
interface AppComponent {

    fun inject(fragment: FragmenEmojiList)

}