package me.loterio.randomemoji.repository.impl.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao
import me.loterio.randomemoji.repository.impl.db.dao.GithubUsersDao
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB
import me.loterio.randomemoji.repository.impl.db.model.GithubUserDB

@Database(entities = [EmojiDB::class, GithubUserDB::class], version = 2)
abstract class AppRoomDatabase : RoomDatabase() {

    internal abstract fun emojiDao(): EmojiDao
    internal abstract fun githubUsersDao(): GithubUsersDao

}