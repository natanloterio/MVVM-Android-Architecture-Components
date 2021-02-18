package me.loterio.randomemoji.repository.impl.db.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.loterio.randomemoji.TestUtil
import me.loterio.randomemoji.repository.impl.db.AppRoomDatabase
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EmojiDaoTest {
    private val NAME: String = "EMOJI_NAME"
    private val URL: String = "EMOJI_URL"
    private lateinit var emojiDao: EmojiDao
    private lateinit var db: AppRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppRoomDatabase::class.java).build()
        emojiDao = db.emojiDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeEmojiAndRead() {
        val user: EmojiDB = TestUtil.createEmojiDB(uid = 3,
            name = NAME,
            url =  URL)

        emojiDao.insert(user)
        val foundEmoji = emojiDao.findByName(name = NAME)

        Assert.assertThat(
            foundEmoji.name,
            CoreMatchers.equalTo(NAME)
        )
    }
}