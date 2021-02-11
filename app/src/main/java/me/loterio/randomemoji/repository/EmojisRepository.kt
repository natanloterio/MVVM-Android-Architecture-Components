package me.loterio.randomemoji.repository

import android.accounts.NetworkErrorException
import com.google.gson.Gson
import me.loterio.randomemoji.model.Emoji
import me.loterio.randomemoji.network.EmojiAPI
import java.lang.Exception

class EmojisRepository(private val emojiEmojiApi: EmojiAPI) {

    suspend fun getAll() : RepositoryResonse<List<Emoji>> {
        var emojisList: List<Pair<String,String>>

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