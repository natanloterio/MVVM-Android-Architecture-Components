package me.loterio.randomemoji.repository.contracts

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.RepositoryResonse

interface EmojisRepository {

    suspend fun getAll() : RepositoryResonse<List<Emoji>>
    fun resetCache()
}