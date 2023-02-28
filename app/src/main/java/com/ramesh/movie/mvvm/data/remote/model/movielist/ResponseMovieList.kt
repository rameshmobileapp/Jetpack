package com.ramesh.movie.mvvm.data.remote.model.movielist

data class ResponseMovieList (
    val page : Int,
    val results : List<Results>,
    val total_pages : Int,
    val total_results : Int
        )