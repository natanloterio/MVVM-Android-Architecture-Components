package me.loterio.randomemoji.repository.impl.db.dao

import androidx.room.*
import androidx.room.FtsOptions.Order
import io.reactivex.Single
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB
import me.loterio.randomemoji.repository.impl.db.model.GithubUserDB


@Dao
interface GithubUsersDao {

    @Query("SELECT * FROM github_user order by uid asc LIMIT 1")
    fun getFirst(): GithubUserDB

    @Query("SELECT * FROM github_user")
    fun getAll(): List<GithubUserDB>

    @Insert
    fun insert(vararg login: GithubUserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(emojis: List<GithubUserDB?>?)

    @Query("DELETE FROM github_user")
    fun deleteAll()

    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun findByName(login: String): GithubUserDB

}