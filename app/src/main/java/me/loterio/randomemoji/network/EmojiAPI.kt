package me.loterio.randomemoji.network

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface EmojiAPI {

    @GET("/emojis")
    suspend fun getAll(): Response<ResponseBody>
}