package com.dio.repositoriesgithub.data.model

import com.danilo.newsapp.domain.model.Repository
import com.google.gson.annotations.SerializedName

data class RepositoryResponse (
    val id: Long,
    val name: String,
    val owner: OwnerResponse,
    val stargazers_count: Long,
    val language: String?,
    val html_url: String,
    val description: String?
){
    fun toData(): Repository {
        return Repository(
            id = id,
            name = name,
            owner = owner.toData(),
            stargazersCount = stargazers_count,
            language = language,
            htmlURL = html_url,
            description = description
        )
    }
}