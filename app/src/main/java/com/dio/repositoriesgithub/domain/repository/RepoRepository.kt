package com.dio.repositoriesgithub.domain.repository

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.Resource

interface RepoRepository {
    suspend fun searchRepositories(search: String): Resource<List<Repository>>
}