package com.ramesh.movie.mvvm.data.local.model

import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_cast")
data class MovieCast(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "movie_serial_numer") val movieserialnumer: Int,

    @ColumnInfo(name = "movie_id")
    val movieid: String,

    @ColumnInfo(name = "movie_cast_name") val moviecastname: String,

    @ColumnInfo(name = "movie_cast_image") val moviecastimage: String?
)

