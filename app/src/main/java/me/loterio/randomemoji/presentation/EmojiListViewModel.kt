package me.loterio.randomemoji.presentation

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.EmojisRepositoryImpl
import me.loterio.randomemoji.repository.RepositoryResonse
import javax.inject.Inject

class EmojiListViewModel @Inject constructor(var emojisRepository: EmojisRepositoryImpl) : ViewModel() {

    val showLoading = ObservableBoolean()
    val showError = MutableLiveData<String>()
    val emojiList = MutableLiveData<List<Emoji>>()

    fun getAllEmojis() {
        showLoading.set(true)
        viewModelScope.launch {
            var result = emojisRepository.getAll()

            showLoading.set(false)
            when(result){
                is RepositoryResonse.Success -> {
                    emojiList.value = result.successData
                    showError.value = null
                }
                is RepositoryResonse.Error -> showError.value = result.exception.message
            }
        }
    }
}