package me.loterio.randomemoji.di

import me.loterio.randomemoji.network.EmojiAPI
import me.loterio.randomemoji.repository.EmojisRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory { provideEmojisRepository(get()) }
}

fun provideEmojisRepository(emojiEmojiApi: EmojiAPI): EmojisRepository = EmojisRepository(emojiEmojiApi)

