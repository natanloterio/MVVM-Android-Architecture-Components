package me.loterio.randomemoji.repository.impl.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emoji")
data class EmojiDB(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String
) {
    constructor(name: String, url: String) : this(0,name,url)
}