package me.loterio.randomemoji.presentation.main

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import me.loterio.randomemoji.repository.EmojisRepositoryImpl
import me.loterio.randomemoji.repository.RepositoryResonse
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(var emojisRepository: EmojisRepository, var githubUsersRepository: GithubUsersRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val showError = MutableLiveData<String>()
    val randomEmoji = MutableLiveData<Emoji>()


    fun showRandomEmoji() {
        showLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var result = emojisRepository.getAll()

            viewModelScope.launch {
                showLoading.set(false)
                when (result) {
                    is RepositoryResonse.Success -> {
                        randomEmoji.value = result.successData.random()
                        showError.value = null
                    }
                    is RepositoryResonse.Error -> showError.value = result.exception.message
                }
            }

        }
    }

    fun searchGithubUser(username: String) {
        showLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var result = githubUsersRepository.searchGithubUser(username)

            viewModelScope.launch {
                showLoading.set(false)
                when (result) {
                    is RepositoryResonse.Success -> {

                        showError.value = null
                    }
                    is RepositoryResonse.Error -> showError.value = result.exception.message
                }
            }

        }
    }
}