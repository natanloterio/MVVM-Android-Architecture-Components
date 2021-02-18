package me.loterio.randomemoji.repository

sealed class RepositoryResonse <out T> {

    data class Success<out T>(val successData : T) : RepositoryResonse<T>()

    class Error(val exception: java.lang.Exception, val message: String = exception.localizedMessage ?: "")
        : RepositoryResonse<Nothing>()

}