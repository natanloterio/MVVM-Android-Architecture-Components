package me.loterio.randomemoji.repository.contracts

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.RepositoryResonse

interface GithubUsersRepository {

    suspend fun getAll(): RepositoryResonse<List<GithubUser>>
    suspend fun searchGithubUser(username: String) : RepositoryResonse<GithubUser>
}