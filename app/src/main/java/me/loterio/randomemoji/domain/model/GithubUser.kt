package me.loterio.randomemoji.domain.model

data class GithubUser(
    val login: String,
    val id: Int,
    val avatar_url: String
)