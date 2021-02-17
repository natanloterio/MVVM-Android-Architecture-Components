package me.loterio.randomemoji.di

import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.presentation.EmojiListViewModel
import me.loterio.randomemoji.repository.EmojisRepositoryImpl

@Module
class ViewModelsModule() {

    @Provides
    fun provideEmojiListViewModel(emojisRepository: EmojisRepositoryImpl): EmojiListViewModel {
        return EmojiListViewModel(emojisRepository)
    }

}