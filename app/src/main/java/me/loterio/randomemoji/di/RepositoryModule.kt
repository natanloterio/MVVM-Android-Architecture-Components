package me.loterio.randomemoji.di

import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.repository.impl.network.EmojiAPIService
import me.loterio.randomemoji.repository.EmojisRepositoryImpl

@Module
class RepositoryModule {

    @Provides
    fun provideEmojisRepository(emojiEmojiApi: EmojiAPIService): EmojisRepositoryImpl {
       return  EmojisRepositoryImpl(emojiEmojiApi)
    }

}