package me.loterio.randomemoji.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.repository.impl.network.EmojiAPIService
import me.loterio.randomemoji.repository.EmojisRepositoryImpl
import me.loterio.randomemoji.repository.impl.db.AppRoomDatabase
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao

@Module
class RepositoryModule {

    @Provides
    fun provideAppRoomDatabase(applicationContext: Context):AppRoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java, "database-test"
        ).build()
    }

    @Provides
    fun provideEmojiDao(appRoomDatabase: AppRoomDatabase): EmojiDao {
        return appRoomDatabase.emojiDao()
    }

    @Provides
    fun provideEmojisRepository(
        emojiEmojiApi: EmojiAPIService,
        emojiDao: EmojiDao): EmojisRepositoryImpl {
       return  EmojisRepositoryImpl(emojiEmojiApi,emojiDao)
    }

}