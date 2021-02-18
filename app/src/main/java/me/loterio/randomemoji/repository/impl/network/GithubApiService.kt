package me.loterio.randomemoji.repository.impl.network

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("/emojis")
    suspend fun getAllEmojis(): List<Emoji>

    @GET("/users/{username}")
    suspend fun searchGithubUser(@Path(value = "username") username: String): GithubUser
}