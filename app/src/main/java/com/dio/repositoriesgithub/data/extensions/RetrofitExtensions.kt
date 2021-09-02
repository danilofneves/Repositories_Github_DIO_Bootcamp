package com.dio.repositoriesgithub.data.extensions

import com.dio.repositoriesgithub.data.exception.*
import retrofit2.Response

fun Response<*>.getThrowable(): ResponseError {
    return with(code()) {
        when {
            this in 400..499 -> ResponseError.ClientErrorException
            this >= 500 -> ResponseError.ServerErrorException
            else -> ResponseError.IOErrorException
        }
    }
}
