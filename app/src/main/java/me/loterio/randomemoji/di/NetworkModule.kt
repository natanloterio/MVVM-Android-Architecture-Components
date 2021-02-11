package me.loterio.randomemoji.di

import me.loterio.randomemoji.BuildConfig
import me.loterio.randomemoji.network.EmojiAPI
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}
