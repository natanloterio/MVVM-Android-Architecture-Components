package me.loterio.randomemoji.presentation.avatarlist

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.RepositoryResonse
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import javax.inject.Inject

class AvatarListViewModel @Inject constructor(var githubUsersRepository: GithubUsersRepository) :
    ViewModel() {

    val showLoading = ObservableBoolean()
    val showError = MutableLiveData<String>()
    val avatarList = MutableLiveData<List<GithubUser>>()

    fun getAllAvatars() {
        showLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var result = githubUsersRepository.getAll()

            viewModelScope.launch {
                showLoading.set(false)
                when (result) {
                    is RepositoryResonse.Success -> {
                        avatarList.value = result.successData
                        showError.value = null
                    }
                    is RepositoryResonse.Error -> showError.value = result.exception.message
                }
            }
        }
    }
}