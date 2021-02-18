package me.loterio.randomemoji.repository.impl.network

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import retrofit2.http.GET

interface GithubApiService {

    @GET("/emojis")
    suspend fun getAllEmojis(): List<Emoji>

    @GET("/users/:username")
    suspend fun searchGithubUser(username: String): GithubUser
}