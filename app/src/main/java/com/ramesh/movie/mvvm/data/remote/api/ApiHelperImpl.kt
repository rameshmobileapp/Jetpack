package com.ramesh.movie.mvvm.data.remote.api

import com.ramesh.movie.mvvm.data.remote.model.screenshot.ResponseScreenshot
import com.ramesh.movie.mvvm.data.remote.model.cast.ResponseCast
import com.ramesh.movie.mvvm.data.remote.model.movielist.ResponseMovieList
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovieList(apiKey: String): Response<ResponseMovieList>  = apiService.getMovieList(apiKey)
    override suspend fun getMovieScreenshot(movieId:String,apiKey: String): Response<ResponseScreenshot> = apiService.getMovieScreenShot(movieId,apiKey)
    override suspend fun getCastList(movieId: String, api: String): Response<ResponseCast>  = apiService.getCastList(movieId,api)
}