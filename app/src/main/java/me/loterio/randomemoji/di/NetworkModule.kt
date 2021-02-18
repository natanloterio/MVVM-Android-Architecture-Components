package me.loterio.randomemoji.di

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.BuildConfig
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.impl.network.EmojiConverterFactory
import me.loterio.randomemoji.repository.impl.network.GithubApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@Module
class NetworkModule {


    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val emojiList: Type = object : TypeToken<MutableList<Emoji>>() {}.type
        val customGsonFactory = GsonBuilder().registerTypeAdapter(emojiList, EmojiConverterFactory()).create()

        return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGsonFactory))
            .build()

    }

    @Provides
    fun provideEmojiApi(retrofit: Retrofit): GithubApiService = retrofit.create(
            GithubApiService::class.java)

}