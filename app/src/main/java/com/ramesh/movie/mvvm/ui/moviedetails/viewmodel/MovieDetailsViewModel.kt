package com.ramesh.movie.mvvm.ui.moviedetails.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.movie.mvvm.data.remote.model.screenshot.ResponseScreenshot
import com.ramesh.movie.mvvm.data.local.model.MovieCast
import com.ramesh.movie.mvvm.data.local.model.MovieScreenShot
import com.ramesh.movie.mvvm.data.remote.model.cast.Cast
import com.ramesh.movie.mvvm.data.remote.model.cast.ResponseCast
import com.ramesh.movie.mvvm.data.repository.Repository
import com.ramesh.movie.mvvm.utils.NetworkHelper
import com.ramesh.movie.mvvm.utils.Resource
import kotlinx.coroutines.launch
import java.sql.SQLException

class MovieDetailsViewModel @ViewModelInject constructor(
    private val mainRepository: Repository, private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _onlineMovieCast = MutableLiveData<Resource<ResponseCast>>()

    private val _onlineMovieScreenshot = MutableLiveData<Resource<ResponseScreenshot>>()
    private val _localMovieScreenShot = MutableLiveData<Resource<List<MovieScreenShot>>>()
    private val _localMovieCast = MutableLiveData<Resource<List<MovieCast>>>()

    val localMovieScreenShot: LiveData<Resource<List<MovieScreenShot>>>
        get() = _localMovieScreenShot

    val onlineMovieScreenshot: LiveData<Resource<ResponseScreenshot>>
        get() = _onlineMovieScreenshot

    val onlineMovieCast: LiveData<Resource<ResponseCast>>
        get() = _onlineMovieCast

    val localMovieCast: LiveData<Resource<List<MovieCast>>>
        get() = _localMovieCast

    internal fun getMovieScreenshot(apiKey: String, movieId: String) {
        viewModelScope.launch {
            _onlineMovieScreenshot.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getMovieScreenshot(movieId, apiKey).let {
                    if (it.isSuccessful) {
                        _onlineMovieScreenshot.postValue(Resource.success(it.body()))
                    } else _onlineMovieScreenshot.postValue(
                        Resource.error(
                            it.errorBody().toString(), null
                        )
                    )
                }
            } else _onlineMovieScreenshot.postValue(Resource.error("No internet connection", null))
        }
    }

    internal fun getMovieCast(movieId: String, apiKey: String) {
        viewModelScope.launch {
            _onlineMovieCast.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getMovieCast(movieId, apiKey).let {
                    if (it.isSuccessful) {
                        _onlineMovieCast.postValue(Resource.success(it.body()))
                    } else _onlineMovieCast.postValue(
                        Resource.error(
                            it.errorBody().toString(), null
                        )
                    )
                }
            } else _onlineMovieCast.postValue(Resource.error("No internet connection", null))
        }
    }


    internal fun insertMovieScreenShot(responseMovieScreenshot: ResponseScreenshot, movieId: String) {
        viewModelScope.launch {
            val localScreenShotImage = responseMovieScreenshot.images.backdrops
            for (model in localScreenShotImage) {
                val movieScreenShot = MovieScreenShot(
                    0, movieId, model.file_path
                )
                mainRepository.insertMovieScreenShot(movieScreenShot)
                _onlineMovieCast.postValue(Resource.success(null))
            }
            getLocalMovieScreenShot(movieId)
        }
    }

    internal fun insertMovieCast(castList: List<Cast>, movieId: Int) {
        viewModelScope.launch {
            val localCastImage = castList
            for (model in localCastImage) {
                val movieCast = MovieCast(
                    0, movieId.toString(),model.name, model.profile_path
                )
                mainRepository.insertMovieCast(movieCast)
            }
            getLocalMovieCast(movieId.toString())
        }
    }

    internal fun getLocalMovieScreenShot(movieId: String) {
        viewModelScope.launch {
            _localMovieScreenShot.postValue(Resource.loading(null))
            try {
                mainRepository.getMovieScreenShot(movieId).let {
                    if(it.isNotEmpty()) {
                        _localMovieScreenShot.postValue(Resource.success(it))
                    }else
                    {
                        _localMovieScreenShot.postValue(Resource.error("No data", null))
                    }
                }
            } catch (sqlexception: SQLException) {
                _localMovieScreenShot.postValue(
                    Resource.error(
                        sqlexception.message.toString(),
                        null
                    )
                )
            }
        }
    }

    internal fun getLocalMovieCast(movieId: String) {
        viewModelScope.launch {
            _localMovieCast.postValue(Resource.loading(null))
            try {
                mainRepository.getMovieCast(movieId).let {
                    if(it.isNotEmpty()) {
                        _localMovieCast.postValue(Resource.success(it))
                    }else
                    {
                        _localMovieCast.postValue(Resource.error("No data", null))
                    }
                }
            } catch (sqlexception: SQLException) {
                _localMovieCast.postValue(
                    Resource.error(
                        sqlexception.message.toString(),
                        null
                    )
                )
            }
        }
    }
}


