package com.ramesh.movie.mvvm.data.remote.model.screenshot



data class ResponseScreenshot(

    val adult : Boolean,
    val backdrop_path : String,
    val budget : Int,
    val release_date : String,
    val runtime : Int,
    val images : Images

)