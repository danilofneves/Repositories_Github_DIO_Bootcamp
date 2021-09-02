package com.dio.repositoriesgithub.data.remote.service

import com.dio.repositoriesgithub.data.model.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("search/repositories")
    suspend fun search(@Query("q") query:String): Response<ResultResponse>
}