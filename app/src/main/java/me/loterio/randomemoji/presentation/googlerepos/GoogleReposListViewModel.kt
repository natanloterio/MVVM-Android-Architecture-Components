package me.loterio.randomemoji.presentation.googlerepos

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.GithubRepo
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
}