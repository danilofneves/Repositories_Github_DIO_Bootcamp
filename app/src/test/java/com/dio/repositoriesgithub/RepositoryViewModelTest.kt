package com.dio.repositoriesgithub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dio.repositoriesgithub.core.DTOGenerate
import com.dio.repositoriesgithub.core.MainCoroutineRule
import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.core.getOrAwaitValue
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.exception.ResponseError
import com.dio.repositoriesgithub.domain.usecases.SearchRepo
import com.dio.repositoriesgithub.presentation.viewmodels.RepositoryViewModel
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repositoryViewModel: RepositoryViewModel

    private lateinit var searchRepo: SearchRepo
    private val testModelsGenerator: DTOGenerate = DTOGenerate()

    @Before
    fun setup() {
        searchRepo = spyk()
        repositoryViewModel =
            RepositoryViewModel(
                searchRepo
            )
    }

    @Test
    fun `search repo with Android query`() {
        val repoModeltest = testModelsGenerator.generateRepositoriesModel()
        val query = "Android"

        coEvery{ searchRepo(query)} returns Resource.Success(data = repoModeltest)

        repositoryViewModel.search(query)
        repositoryViewModel.repoLiveData.observeForever { }

        val isEmptyList = repositoryViewModel.repoLiveData.getOrAwaitValue().data.isNullOrEmpty()
        assert(repoModeltest == repositoryViewModel.repoLiveData.getOrAwaitValue().data)
        assert(!isEmptyList)
    }

    @Test
    fun `search repo with no query`() {
        val repoModeltest = testModelsGenerator.generateRepositoriesModelEmpty()
        val query = ""

        coEvery{ searchRepo(query)} returns Resource.Success(data = repoModeltest)

        repositoryViewModel.search(query)
        repositoryViewModel.repoLiveData.observeForever { }

        val isEmptyList = repositoryViewModel.repoLiveData.getOrAwaitValue().data.isNullOrEmpty()
        assert(repoModeltest == repositoryViewModel.repoLiveData.getOrAwaitValue().data)
        assert(isEmptyList)
    }

    @Test
    fun handleFetchRepoIOError() {
        val error: Resource<List<Repository>> = Resource.DataError(ResponseError.IOErrorException)
        val query = "Android"

        coEvery{ searchRepo(query)} returns error

        repositoryViewModel.search(query)
        repositoryViewModel.repoLiveData.observeForever { }

        assert( repositoryViewModel.repoLiveData.getOrAwaitValue().error is ResponseError.IOErrorException)
    }


}