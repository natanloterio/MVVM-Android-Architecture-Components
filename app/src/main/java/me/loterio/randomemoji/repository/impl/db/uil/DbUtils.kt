package me.loterio.randomemoji.repository.impl.db.uil

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB

fun domainToDB(all: List<Emoji>): List<EmojiDB?>? = all.map { EmojiDB(
    name = it.name,
    url = it.url
) }
