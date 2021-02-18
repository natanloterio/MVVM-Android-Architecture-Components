package me.loterio.randomemoji.repository.impl.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB

@Database(entities = arrayOf(EmojiDB::class), version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    internal abstract fun emojiDao(): EmojiDao

}