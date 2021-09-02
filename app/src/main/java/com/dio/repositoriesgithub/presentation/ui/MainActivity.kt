package com.dio.repositoriesgithub.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.danilo.newsapp.domain.model.Repository
import com.dio.repositoriesgithub.R
import com.dio.repositoriesgithub.core.ViewModelFactory
import com.dio.repositoriesgithub.data.Resource
import com.dio.repositoriesgithub.data.exception.*
import com.dio.repositoriesgithub.databinding.ActivityMainBinding
import com.dio.repositoriesgithub.presentation.extensions.createDialog
import com.dio.repositoriesgithub.presentation.extensions.createProgressDialog
import com.dio.repositoriesgithub.presentation.extensions.hideSoftKeyboard
import com.dio.repositoriesgithub.presentation.extensions.observe
import com.dio.repositoriesgithub.presentation.viewmodels.RepositoryViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val progressDialog by lazy { createProgressDialog() }
    private lateinit var binding: ActivityMainBinding
    private val repoListAdapter by lazy { RepositoryListAdapter() }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: RepositoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)

        viewModel = viewModelFactory.create(RepositoryViewModel::class.java)
        binding.rvRepos.adapter = repoListAdapter
        observe(viewModel.repoLiveData, ::handleRepoSearch)
        observe(viewModel.error, ::handleError)

        setContentView(binding.root)
    }

    private fun handleRepoSearch(status: Resource<List<Repository>>) {
        when (status) {
            is Resource.Loading -> {
                progressDialog.show()
            }
            is Resource.Success -> status.data?.let {
                progressDialog.dismiss()
                repoListAdapter.submitList(it)
            }
            is Resource.DataError -> {
                progressDialog.dismiss()
                viewModel.notifyFailure(status.error)
            }
        }
    }

    private fun handleError(responseError: ResponseError) {
        var msg:String = when (responseError) {
            is ResponseError.ClientErrorException -> getString(R.string.clientError)
            is ResponseError.IOErrorException -> getString(R.string.ioError)
            is ResponseError.ServerErrorException -> getString(R.string.serverError)
        }
        createDialog {
            setMessage(msg)
        }.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.search(it) }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange: $newText")
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }
}