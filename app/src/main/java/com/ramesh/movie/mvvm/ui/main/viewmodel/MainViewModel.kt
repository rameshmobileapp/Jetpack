package com.ramesh.movie.mvvm.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.movie.mvvm.data.local.model.MovieList
import com.ramesh.movie.mvvm.data.remote.model.movielist.ResponseMovieList
import com.ramesh.movie.mvvm.data.repository.Repository
import com.ramesh.movie.mvvm.utils.NetworkHelper
import com.ramesh.movie.mvvm.utils.Resource
import kotlinx.coroutines.launch
import java.sql.SQLException


class MainViewModel  @ViewModelInject constructor(
    private val mainRepository: Repository, private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _localMovieList = MutableLiveData<Resource<List<MovieList>>>()
    val localMovieList: LiveData<Resource<List<MovieList>>>
        get() = _localMovieList

    private val _onlineMovieList = MutableLiveData<Resource<ResponseMovieList>>()
    val movieList: LiveData<Resource<ResponseMovieList>>
        get() = _onlineMovieList



    internal fun getMovieList(apiKey: String) {
        viewModelScope.launch {
            _onlineMovieList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getMovieList(apiKey).let {
                    if (it.isSuccessful) {
                        _onlineMovieList.postValue(Resource.success(it.body()))
                    } else _onlineMovieList.postValue(
                        Resource.error(
                            it.errorBody().toString(), null
                        )
                    )
                }
            } else _onlineMovieList.postValue(Resource.error("No internet connection", null))
        }
    }

    internal fun insertMovieList(responseMovieList: ResponseMovieList) {
        viewModelScope.launch {
            responseMovieList.let {
                val onlineModel = responseMovieList.results
                for (model in onlineModel) {
                    val localModel = MovieList(
                        0,
                        model.title,
                        model.vote_average.toString(),
                        model.vote_count.toString(),
                        model.popularity.toString(),
                        model.backdrop_path,
                        model.poster_path,
                        model.id.toString(),
                        model.overview
                    )
                    mainRepository.insertMovie(localModel)
                }
                getMovieListFromLocal()
            }
        }
    }

    private fun getMovieListFromLocal() {
        viewModelScope.launch {
            _localMovieList.postValue(Resource.loading(null))
            try {
                mainRepository.getMovieList().let {
                    if (it.isNotEmpty()) {
                        _localMovieList.postValue(Resource.success(it))
                    } else {
                        _localMovieList.postValue(Resource.error("Ramesh.....", null))
                    }
                }
            } catch (sqlexception: SQLException) {
                _localMovieList.postValue(Resource.error(sqlexception.message.toString(), null))
            }
        }
    }
}