package me.loterio.randomemoji.presentation.emojilist

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.repository.EmojisRepositoryImpl
import me.loterio.randomemoji.repository.RepositoryResonse
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import okhttp3.internal.wait
import javax.inject.Inject

class EmojiListViewModel @Inject constructor(var emojisRepository: EmojisRepository) :
    ViewModel() {

    val showLoading = ObservableBoolean()
    val showError = MutableLiveData<String>()
    val emojiList = MutableLiveData<List<Emoji>>()

   fun resetCache() {
       emojiList.postValue(listOf())
       viewModelScope.launch(Dispatchers.IO) {
       emojisRepository.resetCache()
           viewModelScope.launch {
               getAllEmojis()
           }
       }

   }

    fun getAllEmojis() {
        showLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var result = emojisRepository.getAll()

            viewModelScope.launch {
                showLoading.set(false)
                when (result) {
                    is RepositoryResonse.Success -> {
                        emojiList.value = result.successData
                        showError.value = null
                    }
                    is RepositoryResonse.Error -> showError.value = result.exception.message
                }
            }
        }
    }
}