package com.dio.repositoriesgithub.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.exception.ResponseError
import com.dio.repositoriesgithub.domain.usecases.SearchRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    private val searchRepo: SearchRepo
): ViewModel(){

    private val _repoLiveData: MutableLiveData<Resource<List<Repository>>> = MutableLiveData()
    val repoLiveData: LiveData<Resource<List<Repository>>> get() = _repoLiveData

    val error = MutableLiveData<ResponseError>()

    fun search(query: String){
        viewModelScope.launch {
            _repoLiveData.value = Resource.Loading()
            _repoLiveData.value = searchRepo(query)
        }
    }

    fun notifyFailure(responseError: ResponseError?) {
        error.postValue(responseError!!)
    }
}