package me.loterio.randomemoji.repository

import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import me.loterio.randomemoji.repository.impl.db.dao.EmojiDao
import me.loterio.randomemoji.repository.impl.db.dao.GithubUsersDao
import me.loterio.randomemoji.repository.impl.db.model.GithubUserDB
import me.loterio.randomemoji.repository.impl.db.uil.userDomainToDb
import me.loterio.randomemoji.repository.impl.network.GithubApiService

class GithubUsersRepositoryImpl(
        var apiService: GithubApiService,
        var githubUsersDao: GithubUsersDao
): GithubUsersRepository{

    override suspend fun getAll() : RepositoryResonse<List<GithubUser>> {
        return try {
            RepositoryResonse.Success(getUsersLocally())
        }catch (e: Exception){
            e.printStackTrace()
            RepositoryResonse.Error(
                exception = e,
                message = "Error while retrieving data"
            )
        }
    }

    override suspend fun searchGithubUser(username: String): RepositoryResonse<GithubUser> {
        return try {
            val cachedUser: GithubUserDB = githubUsersDao.findByName(username)
            if(cachedUser != null){
                RepositoryResonse.Success(GithubUser(
                        login = cachedUser.login,
                        id = cachedUser.id,
                        avatar_url = cachedUser.avatarUrl
                ))
            }else{

                val user = apiService.searchGithubUser(username = username)
                githubUsersDao.insert(userDomainToDb(user))

                RepositoryResonse.Success(user)
            }
        }catch (e: Exception){
            e.printStackTrace()
            RepositoryResonse.Error(
                    exception = e,
                    message = "Error while retrieving data"
            )
        }
    }

    private fun getUsersLocally(): List<GithubUser> {
        return githubUsersDao.getAll().map {
            GithubUser(
                login = it.login,
                id = it.id,
                avatar_url = it.avatarUrl)
        }
    }



}