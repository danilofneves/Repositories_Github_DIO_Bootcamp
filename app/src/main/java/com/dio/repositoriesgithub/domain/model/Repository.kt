package com.danilo.newsapp.domain.model

import com.dio.repositoriesgithub.domain.model.Owner

data class Repository(
    var id: Long,
    var name: String,
    var owner: Owner,
    var stargazersCount: Long,
    var language: String?,
    var htmlURL: String,
    var description: String?
)