package me.loterio.randomemoji.repository

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao
import me.loterio.randomemoji.repository.impl.db.uil.emojiDomainToDB
import me.loterio.randomemoji.repository.impl.network.GithubApiService

class EmojisRepositoryImpl(
    var apiService: GithubApiService,
    var emojiDao: EmojiDao
): EmojisRepository{

    override suspend fun getAll() : RepositoryResonse<List<Emoji>> {
        return try {
            if(hasCachedData()){
                RepositoryResonse.Success(getEmojisLocally())
            }else {
                RepositoryResonse.Success(saveEmojisLocally(getEmojisRemotelly()))
            }
        }catch (e: Exception){
            e.printStackTrace()
            RepositoryResonse.Error(
                exception = e,
                message = "Error while retrieving data"
            )
        }
    }

    private suspend fun getEmojisRemotelly() = apiService.getAllEmojis()

    private fun hasCachedData(): Boolean {
        var first = emojiDao.getFirst()
        return first != null
    }

    private fun saveEmojisLocally(all: List<Emoji>): List<Emoji> {
        emojiDao.insertAll(emojiDomainToDB(all))
        return all
    }

    private fun getEmojisLocally(): List<Emoji> {
        return emojiDao.getAll().map {
            Emoji(
                name = it.name,
                url = it.url)
        }
    }

    override fun resetCache() {
        emojiDao.deleteAll()
    }


}