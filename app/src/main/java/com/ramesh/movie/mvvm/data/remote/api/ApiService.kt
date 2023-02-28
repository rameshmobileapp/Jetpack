package com.ramesh.movie.mvvm.data.remote.api

import com.ramesh.movie.mvvm.data.remote.model.screenshot.ResponseScreenshot
import com.ramesh.movie.mvvm.data.remote.model.cast.ResponseCast
import com.ramesh.movie.mvvm.data.remote.model.movielist.ResponseMovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("/3/discover/movie?")
    suspend fun getMovieList(@Query("api_key")  api:String): Response<ResponseMovieList>

    @GET("/3/movie/{movie_id}?append_to_response=images")
    suspend fun getMovieScreenShot(@Path(value = "movie_id")  movieId:String,@Query("api_key")  api:String): Response<ResponseScreenshot>

    @GET("/3/movie/{movie_id}/casts?")
    suspend fun getCastList(@Path(value = "movie_id")  movieId:String,@Query("api_key")  api:String): Response<ResponseCast>

}