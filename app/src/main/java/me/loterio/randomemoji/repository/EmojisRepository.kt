package me.loterio.randomemoji.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.loterio.randomemoji.core.log
import me.loterio.randomemoji.network.EmojiAPI
import okhttp3.ResponseBody
import org.json.JSONObject
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class EmojisRepository(private val emojiEmojiApi: EmojiAPI) {
    suspend fun getAll() : List<Map<String, String>> {
        val response = emojiEmojiApi.getAll()
        if(response.isSuccessful){
            val strJSON = response.body()?.string()//?.replace("{","")?.replace("}","")
            //val objJSON = Gson().fromJson(strJSON,JSONObject::class.java)
            val map: java.util.HashMap<*, *>? = Gson().fromJson(strJSON,HashMap::class.java)

            val list = map?.toList();


            strJSON?.let { log(it) }
        }
        return ArrayList<Map<String,String>>(0)
    }
}