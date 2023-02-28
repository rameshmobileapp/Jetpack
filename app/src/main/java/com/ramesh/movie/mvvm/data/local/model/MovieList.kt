package com.ramesh.movie.mvvm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list")
data class MovieList(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "movie_serial_numer") val movieserialnumer: Int,

    @ColumnInfo(name = "movie_name") val moviename: String,

    @ColumnInfo(name = "movie_rate") val movierate: String,

    @ColumnInfo(name = "movie_count") val moviecount: String,

    @ColumnInfo(name = "movie_popularity") val moviepopularity: String,

    @ColumnInfo(name = "movie_stil") val moviestill: String,

    @ColumnInfo(name = "movie_poster") val movieposter: String,

    @ColumnInfo(name = "movie_id") val movieid: String,

    @ColumnInfo(name = "movie_over_view") val movieoverview: String

)