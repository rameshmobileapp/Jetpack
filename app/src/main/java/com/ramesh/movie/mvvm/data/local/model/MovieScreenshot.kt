package com.ramesh.movie.mvvm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_screen_shot")
data class MovieScreenShot(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_serial_numer")
    val movieserialnumer: Int,

    @ColumnInfo(name = "movie_id")
    val movieid: String,

    @ColumnInfo(name = "movie_screen_shot")
    val movieoscreenshot: String
)