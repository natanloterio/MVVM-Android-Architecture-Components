package me.loterio.randomemoji.repository

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.impl.network.EmojiAPIService

class EmojisRepositoryImpl(private val emojiEmojiApi: EmojiAPIService): EmojisRepository{

    override suspend fun getAll() : RepositoryResonse<List<Emoji>> {
        try {
            return RepositoryResonse.Success(emojiEmojiApi.getAll())
        }catch (e: Exception){
            e.printStackTrace()
            return RepositoryResonse.Error(
                exception = e,
                message = "Error while retrieving data"
            )
        }
    }
}