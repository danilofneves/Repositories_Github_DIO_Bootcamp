package com.dio.repositoriesgithub.domain.di

import com.dio.repositoriesgithub.domain.repository.RepoRepository
import com.dio.repositoriesgithub.domain.usecases.SearchRepo
import com.dio.repositoriesgithub.domain.usecases.SearchRepoImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFetchNews(repository: RepoRepository): SearchRepo = SearchRepoImpl(repository)

}