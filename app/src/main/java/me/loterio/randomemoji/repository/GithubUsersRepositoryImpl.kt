package me.loterio.randomemoji.repository

import androidx.paging.PagingSource
import me.loterio.randomemoji.domain.model.GithubRepo
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import me.loterio.randomemoji.repository.impl.db.dao.GithubUsersDao
import me.loterio.randomemoji.repository.impl.db.model.GithubUserDB
import me.loterio.randomemoji.repository.impl.db.uil.userDomainToDb
import me.loterio.randomemoji.repository.impl.network.GithubApiService
import okio.IOException
import retrofit2.HttpException

class GithubUsersRepositoryImpl(
        var apiService: GithubApiService,
        var githubUsersDao: GithubUsersDao
): PagingSource<Int, GithubRepo>(), GithubUsersRepository{

    private val STARTING_PAGE_INDEX: Int = 1

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
            RepositoryResonse.Success(
                GithubUser(
                    login = cachedUser.login,
                    id = cachedUser.id,
                    avatar_url = cachedUser.avatarUrl
                )
            )
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

    override suspend fun getAllGoogleRepos(): RepositoryResonse<List<GithubRepo>> {
        return try {
                RepositoryResonse.Success(getReposRemotelly("google"))
        }catch (e: Exception){
            e.printStackTrace()
            RepositoryResonse.Error(
                exception = e,
                message = "Error while retrieving data"
            )
        }
    }

    private suspend fun getReposRemotelly(username: String): List<GithubRepo>  =  apiService.getUserRepos(username = username,page = 1,size = 10)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
        return try {
            val result = apiService.getUserRepos("google",params.key ?: STARTING_PAGE_INDEX,10)
            LoadResult.Page(
                data = result,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}