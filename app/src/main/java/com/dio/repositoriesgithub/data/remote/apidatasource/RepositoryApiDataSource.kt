package com.dio.repositoriesgithub.data.remote.apidatasource

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.model.RepositoryResponse

interface RepositoryApiDataSource {

    suspend fun searchRepositories(search: String): Resource<List<Repository>>
}