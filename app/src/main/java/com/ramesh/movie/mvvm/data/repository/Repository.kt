package com.ramesh.movie.mvvm.data.repository

import com.ramesh.movie.mvvm.data.local.dao.DatabaseDAO
import com.ramesh.movie.mvvm.data.local.model.MovieCast
import com.ramesh.movie.mvvm.data.local.model.MovieList
import com.ramesh.movie.mvvm.data.local.model.MovieScreenShot
import com.ramesh.movie.mvvm.data.remote.api.ApiHelper
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dao: DatabaseDAO
) {

    suspend fun getMovieList(apiKey:String) =  apiHelper.getMovieList(apiKey)
    suspend fun getMovieScreenshot(movieId:String,apiKey:String) =  apiHelper.getMovieScreenshot(movieId,apiKey)
    suspend fun getMovieCast(movieId:String,apiKey:String) =  apiHelper.getCastList(movieId,apiKey)

    // Local database
    suspend fun insertMovie(movieList: MovieList) = dao.insertMovieList(movieList)
    suspend fun getMovieList() : List<MovieList> = dao.getMovieList()
    suspend fun insertMovieCast(movieCast: MovieCast) = dao.insertMovieCast(movieCast)
    suspend fun insertMovieScreenShot(movieScreenshot: MovieScreenShot) = dao.insertMovieScreenshot(movieScreenshot)
    suspend fun getMovieScreenShot(movieId:String) :List<MovieScreenShot> = dao.getMovieScreenShot(movieId)
    suspend fun getMovieCast(movieId:String) :List<MovieCast> = dao.getMovieCast(movieId)

}