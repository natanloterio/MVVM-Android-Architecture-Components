package me.loterio.randomemoji.presentation

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import me.loterio.randomemoji.model.Emoji
import me.loterio.randomemoji.repository.EmojisRepository
import me.loterio.randomemoji.repository.RepositoryResonse
import org.koin.dsl.module

val emojiListViewModel = module {
    factory { EmojiListViewModel(get()) }
}

class EmojiListViewModel(
    private val emojisRepository: EmojisRepository
) : ViewModel() {

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