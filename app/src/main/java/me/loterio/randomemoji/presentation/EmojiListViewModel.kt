package me.loterio.randomemoji.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.loterio.randomemoji.repository.EmojisRepository
import org.koin.dsl.module
import retrofit2.Response

val emojiListViewModel = module {
    factory { EmojiListViewModel(get()) }
}
class EmojiListViewModel(
    private val emojisRepository: EmojisRepository
): ViewModel() {

    val emojiList: LiveData<List<Map<String, String>>> = liveData { emit(emojisRepository.getAll()) }
}