package com.dio.repositoriesgithub.data.model

data class ResultResponse (
    val total_count: String = "",
    val incomplete_results: Boolean = false,
    val items: List<RepositoryResponse> = listOf()
)