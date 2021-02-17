package me.loterio.randomemoji.network

import me.loterio.randomemoji.domain.model.Emoji
import retrofit2.http.GET

interface EmojiAPI {

    @GET("/emojis")
    suspend fun getAll(): List<Emoji>
}