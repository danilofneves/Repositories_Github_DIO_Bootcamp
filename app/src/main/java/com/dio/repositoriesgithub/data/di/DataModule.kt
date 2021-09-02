package com.dio.repositoriesgithub.data.di

import com.dio.repositoriesgithub.data.remote.apidatasource.RepositoryApiDataSource
import com.dio.repositoriesgithub.data.remote.apidatasource.retrofit.RepositoryApiDataSourceRetro
import com.dio.repositoriesgithub.data.repository.RepoRepositoryImpl
import com.dio.repositoriesgithub.domain.repository.RepoRepository
import com.dio.repositoriesgithub.data.remote.service.RepositoryService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    private val BASE_URL = "https://api.github.com/"
    private val timeoutConnect = 3   //In seconds
    private val timeoutRead = 3   //In seconds

    @Provides
    fun provideOkHttpBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideServiceNewsService(retrofit: Retrofit ) : RepositoryService {
        return retrofit.create(RepositoryService::class.java)
    }

    @Singleton
    @Provides
    fun bindNewsApiDataSource(
            repoService: RepositoryService
    ): RepositoryApiDataSource = RepositoryApiDataSourceRetro(repoService)

    @Singleton
    @Provides
    fun bindNewsRepository(repoApiDataSource: RepositoryApiDataSource): RepoRepository = RepoRepositoryImpl(repoApiDataSource)


}