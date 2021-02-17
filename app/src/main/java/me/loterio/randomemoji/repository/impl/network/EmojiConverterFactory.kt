package me.loterio.randomemoji.repository.impl.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import me.loterio.randomemoji.domain.model.Emoji
import java.lang.Exception
import java.lang.reflect.Type

class EmojiConverterFactory: JsonDeserializer<List<Emoji>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): List<Emoji>? {
        try{
            val map: java.util.HashMap<*, *>? = Gson().fromJson(json,HashMap::class.java)
            val pairList = map?.toList() as List<Pair<String, String>>?
            val emojiList: List<Emoji>? = pairList?.map { Emoji(
                name = it.first,
                url = it.second)
            }
            return emojiList
        }catch (e: Exception){
            throw RuntimeException("Unexpected JSON type: " + json.javaClass)
        }
    }

}