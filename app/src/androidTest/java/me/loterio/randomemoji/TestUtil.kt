package me.loterio.randomemoji

import me.loterio.randomemoji.repository.impl.db.model.EmojiDB

class TestUtil {

    companion object {
        internal fun createEmojiDB(uid: Int, name: String, url: String): EmojiDB{
            return EmojiDB(
                uid = uid,
                name = name,
                url = url
            )
        }
    }

}
