package me.loterio.randomemoji.repository.impl.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user")
data class GithubUserDB(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String
) {
    constructor(login: String, id: Int, avatarUrl: String) : this(0,login,id,avatarUrl)
}