package com.dio.repositoriesgithub.domain.usecases

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.domain.repository.RepoRepository
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.domain.usecases.SearchRepo
import javax.inject.Inject

class SearchRepoImpl  @Inject constructor(
    private val repository: RepoRepository
) : SearchRepo {
    override suspend operator fun invoke(search: String): Resource<List<Repository>> = repository.searchRepositories(search)
}