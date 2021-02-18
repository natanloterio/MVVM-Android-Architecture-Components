package me.loterio.randomemoji.repository.impl.db.uil

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.impl.db.model.EmojiDB
import me.loterio.randomemoji.repository.impl.db.model.GithubUserDB

fun emojiDomainToDB(all: List<Emoji>): List<EmojiDB?>? = all.map { EmojiDB(
    name = it.name,
    url = it.url
) }



fun userDomainToDb(user: GithubUser): GithubUserDB = GithubUserDB(
    login = user.login,
    id = user.id,
    avatarUrl = user.avatar_url
)
