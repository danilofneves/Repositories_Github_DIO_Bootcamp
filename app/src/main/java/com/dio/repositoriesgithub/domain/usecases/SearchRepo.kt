package com.dio.repositoriesgithub.domain.usecases

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.Resource

interface SearchRepo {
    suspend operator fun invoke(search: String): Resource<List<Repository>>
}