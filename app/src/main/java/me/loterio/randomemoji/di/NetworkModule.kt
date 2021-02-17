package me.loterio.randomemoji.di

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.BuildConfig
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.network.EmojiAPI
import me.loterio.randomemoji.network.EmojiConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    fun provideEmojiApi(retrofit: Retrofit): EmojiAPI = retrofit.create(
        EmojiAPI::class.java)


    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()

    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val emojiList: Type = object : TypeToken<MutableList<Emoji>>() {}.type
        val customGsonFactory = GsonBuilder().registerTypeAdapter(emojiList, EmojiConverterFactory()).create()


        return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGsonFactory))
            .build()

    }




}