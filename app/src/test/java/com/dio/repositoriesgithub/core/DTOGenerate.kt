package com.dio.repositoriesgithub.core

import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.model.ResultResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class DTOGenerate {
    companion object {
        private const val REPOURI = "RepositoriesResponse.json"
    }
    private var result: ResultResponse = ResultResponse()

    init {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<ResultResponse> = moshi.adapter(ResultResponse::class.java)
        val jsonString = getStringJson(REPOURI)
        adapter.fromJson(jsonString)?.let {
            result = it
        }
    }

    fun generateRepositoriesModel(): List<Repository> {
        return result.items.map { it.toData() }
    }

    fun generateRepositoriesModelEmpty(): List<Repository> {
        return listOf()
    }
}