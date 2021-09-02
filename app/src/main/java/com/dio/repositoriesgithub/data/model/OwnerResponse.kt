package com.dio.repositoriesgithub.data.model

import com.dio.repositoriesgithub.domain.model.Owner

data class OwnerResponse(
    val login: String,
    val avatar_url: String
){
    fun toData(): Owner {
        return Owner(
            login = login,
            avatarURL = avatar_url
        )
    }
}