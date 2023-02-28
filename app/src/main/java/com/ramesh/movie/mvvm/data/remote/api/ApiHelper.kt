package com.ramesh.movie.mvvm.data.remote.api

import com.ramesh.movie.mvvm.data.remote.model.screenshot.ResponseScreenshot
import com.ramesh.movie.mvvm.data.remote.model.cast.ResponseCast
import com.ramesh.movie.mvvm.data.remote.model.movielist.ResponseMovieList
import retrofit2.Response

interface ApiHelper {

    suspend fun getMovieList(api:String): Response<ResponseMovieList>
    suspend fun getMovieScreenshot(movieId:String,api:String): Response<ResponseScreenshot>
    suspend fun getCastList(movieId:String,api:String): Response<ResponseCast>
}