package com.ramesh.movie.mvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramesh.movie.mvvm.data.local.model.MovieCast
import com.ramesh.movie.mvvm.data.local.model.MovieList
import com.ramesh.movie.mvvm.data.local.model.MovieScreenShot


@Dao
interface DatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieList(movieList: MovieList)

    @Query("SELECT * FROM movie_list")
    suspend fun getMovieList(): List<MovieList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieScreenshot(movieScreenShot: MovieScreenShot)

    @Query("SELECT * FROM movie_screen_shot where movie_id = :movieId")
    suspend fun getMovieScreenShot(movieId:String): List<MovieScreenShot>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieCast(movieCast: MovieCast)

    @Query("SELECT * FROM movie_cast where movie_id = :movieId")
    suspend fun getMovieCast(movieId:String): List<MovieCast>
}