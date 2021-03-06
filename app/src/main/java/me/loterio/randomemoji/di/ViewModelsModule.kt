package me.loterio.randomemoji.di

import dagger.Module
import dagger.Provides
import me.loterio.randomemoji.presentation.googlerepos.GoogleReposListViewModel
import me.loterio.randomemoji.presentation.emojilist.EmojiListViewModel
import me.loterio.randomemoji.presentation.main.MainViewModel
import me.loterio.randomemoji.repository.contracts.EmojisRepository
import me.loterio.randomemoji.repository.contracts.GithubUsersRepository

@Module
class ViewModelsModule() {

    @Provides
    fun provideEmojiListViewModel(emojisRepository: EmojisRepository): EmojiListViewModel {
        return EmojiListViewModel(
            emojisRepository
        )
    }

    @Provides
    fun provideMainViewModel(
        emojisRepository: EmojisRepository,
        githubUsersRepository: GithubUsersRepository
    ): MainViewModel {
        return MainViewModel(
            emojisRepository,
            githubUsersRepository
        )
    }
    @Provides
    fun provideGoogleReposListViewModel(
        githubUsersRepository: GithubUsersRepository
    ): GoogleReposListViewModel {
        return GoogleReposListViewModel(
            githubUsersRepository
        )
    }
}