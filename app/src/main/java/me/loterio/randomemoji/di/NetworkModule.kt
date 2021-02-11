package me.loterio.randomemoji.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken
import me.loterio.randomemoji.BuildConfig
import me.loterio.randomemoji.model.Emoji
import me.loterio.randomemoji.network.EmojiAPI
import me.loterio.randomemoji.network.EmojiConverterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideEmojiApi(get()) }
}

fun provideEmojiApi(retrofit: Retrofit): EmojiAPI = retrofit.create(
    EmojiAPI::class.java)

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    val emojiList: Type = object : TypeToken<MutableList<Emoji>>() {}.type
    val customGsonFactory = GsonBuilder().registerTypeAdapter(emojiList, EmojiConverterFactory()).create()


    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(customGsonFactory))
        .build()
}
