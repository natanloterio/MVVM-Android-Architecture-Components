package me.loterio.randomemoji.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.repository.EmojisRepositoryImpl
import me.loterio.randomemoji.repository.GithubUsersRepositoryImpl
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import me.loterio.randomemoji.repository.impl.db.AppRoomDatabase
import me.loterio.randomemoji.repository.impl.db.MIGRATION_1_2
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao
import me.loterio.randomemoji.repository.impl.db.dao.GithubUsersDao
import me.loterio.randomemoji.repository.impl.network.GithubApiService

@Module
class RepositoryModule {

    @Provides
    fun provideAppRoomDatabase(applicationContext: Context):AppRoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java, "database-test"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideEmojiDao(appRoomDatabase: AppRoomDatabase): EmojiDao {
        return appRoomDatabase.emojiDao()
    }

    @Provides
    fun provideGithubUsersDao(appRoomDatabase: AppRoomDatabase): GithubUsersDao {
        return appRoomDatabase.githubUsersDao()
    }

    @Provides
    fun provideEmojisRepository(
            apiService: GithubApiService,
            emojiDao: EmojiDao): EmojisRepository {
       return  EmojisRepositoryImpl(apiService,emojiDao)
    }

    @Provides
    fun provideGithubUsersRepository(
         apiService: GithubApiService,
         githubUsersDao: GithubUsersDao ): GithubUsersRepository{
        return  GithubUsersRepositoryImpl(apiService,githubUsersDao)
    }

}