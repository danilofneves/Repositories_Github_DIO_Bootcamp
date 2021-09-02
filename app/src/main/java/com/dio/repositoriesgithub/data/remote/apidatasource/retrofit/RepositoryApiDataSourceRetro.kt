package com.dio.repositoriesgithub.data.remote.apidatasource.retrofit

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.exception.ResponseError
import com.dio.repositoriesgithub.data.remote.apidatasource.RepositoryApiDataSource
import com.dio.repositoriesgithub.data.remote.helper.safeCall
import com.dio.repositoriesgithub.data.model.ResultResponse
import com.dio.repositoriesgithub.data.model.RepositoryResponse
import com.dio.repositoriesgithub.data.remote.service.RepositoryService
import javax.inject.Inject

class RepositoryApiDataSourceRetro @Inject constructor(
    private val userService: RepositoryService
) : RepositoryApiDataSource {

    override suspend fun searchRepositories(search:String): Resource<List<Repository>> {
        return when (val response = safeCall {userService.search(search)}) {
            is ResultResponse -> {
                Resource.Success(data = response.items.map {
                    it.toData()
                })
            }
            else -> {
                Resource.DataError(response as ResponseError)
            }
        }
    }

}