package me.loterio.randomemoji.repository.impl.network

import me.loterio.randomemoji.domain.model.Emoji
import retrofit2.http.GET

interface EmojiAPIService {

    @GET("/emojis")
    suspend fun getAll(): List<Emoji>
}