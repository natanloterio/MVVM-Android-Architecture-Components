package me.loterio.randomemoji.di

import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.presentation.EmojiListViewModel
import me.loterio.randomemoji.repository.EmojisRepository

@Module
class ViewModelsModule() {

    @Provides
    fun provideEmojiListViewModel(emojisRepository: EmojisRepository): EmojiListViewModel {
        return EmojiListViewModel(emojisRepository)
    }
    
}