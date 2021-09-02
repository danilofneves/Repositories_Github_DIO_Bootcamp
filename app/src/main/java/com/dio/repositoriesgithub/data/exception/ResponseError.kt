package com.dio.repositoriesgithub.data.exception

sealed class ResponseError(){
    object ClientErrorException: ResponseError()
    object IOErrorException: ResponseError()
    object ServerErrorException: ResponseError()
}