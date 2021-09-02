package com.dio.repositoriesgithub.data.repository

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.domain.repository.RepoRepository
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.remote.apidatasource.RepositoryApiDataSource
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor (private val repositoriesApiDataSource: RepositoryApiDataSource):
    RepoRepository {
    override suspend fun searchRepositories(search: String): Resource<List<Repository>> {
        return repositoriesApiDataSource.searchRepositories(search)
    }
}