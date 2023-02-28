package com.ramesh.movie.mvvm.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramesh.movie.mvvm.data.local.model.MovieCast
import com.ramesh.movie.mvvm.data.local.model.MovieList
import com.ramesh.movie.mvvm.data.local.model.MovieScreenShot

@Database(entities = [MovieList::class, MovieScreenShot::class, MovieCast::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun DatabaseDAO(): DatabaseDAO
}