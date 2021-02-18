package me.loterio.randomemoji.presentation.googlerepos

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.GithubRepo
import me.loterio.randomemoji.repository.GithubUsersRepositoryImpl
import me.loterio.randomemoji.repository.RepositoryResonse
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import javax.inject.Inject

class GoogleReposListViewModel @Inject constructor(var githubUsersRepository: GithubUsersRepository) :
    ViewModel() {

    val showLoading = ObservableBoolean()
    val showError = MutableLiveData<String>()
    val reposList = MutableLiveData<List<GithubRepo>>()


    fun getAllGoogleRepos() {
        showLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var result = githubUsersRepository.getAllGoogleRepos()

            viewModelScope.launch {
                showLoading.set(false)
                when (result) {
                    is RepositoryResonse.Success -> {
                        reposList.value = result.successData
                        showError.value = null
                    }
                    is RepositoryResonse.Error -> showError.value = result.exception.message
                }
            }
        }
    }


    fun getAllGoogleReposPaged(): Flow<PagingData<GithubRepo>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 500),
            pagingSourceFactory = { githubUsersRepository as GithubUsersRepositoryImpl }
        ).flow.cachedIn(viewModelScope)
    }
}