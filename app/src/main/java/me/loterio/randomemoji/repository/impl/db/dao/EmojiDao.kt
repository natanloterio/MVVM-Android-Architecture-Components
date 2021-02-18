package me.loterio.randomemoji.repository.impl.db.dao

import androidx.room.*
import androidx.room.FtsOptions.Order
import io.reactivex.Single
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB


@Dao
interface EmojiDao {

    @Query("SELECT * FROM emoji order by uid asc LIMIT 1")
    fun getFirst(): EmojiDB

    @Query("SELECT * FROM emoji")
    fun getAll(): List<EmojiDB>

    @Query("SELECT * FROM emoji WHERE uid IN (:emojisIds)")
    fun loadAllByIds(emojisIds: IntArray): List<EmojiDB>

    @Insert
    fun insert(vararg users: EmojiDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(emojis: List<EmojiDB?>?)

    @Delete
    fun delete(emoji: EmojiDB)

    @Query("SELECT * FROM emoji WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): EmojiDB

}