package me.loterio.randomemoji.domain.model

data class GithubUser(
    val login: String,
    val id: Long,
    val avatar_url: String
)