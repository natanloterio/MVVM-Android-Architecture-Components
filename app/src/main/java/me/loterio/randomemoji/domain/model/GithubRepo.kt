package me.loterio.randomemoji.domain.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    val private: Boolean
)