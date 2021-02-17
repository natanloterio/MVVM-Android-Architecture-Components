package me.loterio.randomemoji.di

import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.network.EmojiAPI
import me.loterio.randomemoji.repository.EmojisRepository

@Module
class RepositoryModule {

    @Provides
    fun provideEmojisRepository(emojiEmojiApi: EmojiAPI): EmojisRepository {
       return  EmojisRepository(emojiEmojiApi)
    }

}