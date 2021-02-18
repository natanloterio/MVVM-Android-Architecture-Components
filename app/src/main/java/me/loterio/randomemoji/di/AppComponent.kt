package me.loterio.randomemoji.di

import dagger.Component
import me.loterio.randomemoji.presentation.avatarlist.FragmentAvatarList
import me.loterio.randomemoji.presentation.emojilist.FragmenEmojiList
import me.loterio.randomemoji.presentation.main.FragmentMain
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidCoreModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelsModule::class
])
interface AppComponent {

    fun injectFragmentMain(fragment: FragmentMain)
    fun injectFragmentEmojiList(fragment: FragmenEmojiList)
    fun injectFragmentAvatarList(fragment: FragmentAvatarList)

}