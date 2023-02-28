package com.ramesh.movie.mvvm.ui.moviedetails.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.mvvm.ui.moviedetails.adapter.AdapterCast
import com.mindorks.framework.mvvm.ui.moviedetails.adapter.AdapterScreenShot
import com.ramesh.movie.mvvm.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.ramesh.movie.mvvm.utils.Constant
import com.ramesh.movie.R
import com.ramesh.movie.databinding.ActivityMovieDetailsBinding
import com.ramesh.movie.mvvm.ui.base.BaseActivity
import com.ramesh.movie.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {

    private  val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    private lateinit var binding: ActivityMovieDetailsBinding

    private lateinit var adapterScreenShot: AdapterScreenShot
    private lateinit var adapterCast: AdapterCast

    private var movieName: String? = null
    private var movieRate: String? = null
    private var movieVote: String? = null
    private var moviePopularity: String? = null
    private var movieOverview: String? = null
    private var movieStill: String? = null
    private var moviePoster: String? = null
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewBinding()
        setUpDataBinding()
        getIntentData()
        setIntentData()
        setupObserver()
    }
    override fun setUpViewBinding() {
    }

    override fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
    }

    private fun getIntentData() {
        if (intent != null) {
            movieOverview = intent.getStringExtra(Constant.MOVIE_OVER_VIEW)
            movieStill = intent.getStringExtra(Constant.MOVIE_STILL)
            moviePoster = intent.getStringExtra(Constant.MOVIE_POSTER)
            movieId = intent.getIntExtra(Constant.MOVIE_ID, 0)
            movieRate = intent.getStringExtra(Constant.MOVIE_RATE)
            movieVote = intent.getStringExtra(Constant.MOVIE_VOTE)
            moviePopularity = intent.getStringExtra(Constant.MOVIE_POPULARITY)
            movieName = intent.getStringExtra(Constant.MOVIE_NAME)
        }
    }

    private fun setIntentData() {
        binding.still = movieStill
        binding.poster = moviePoster
        binding.tittle = movieName
        binding.tittle = movieName
        binding.rate = movieRate.plus("/10")
        binding.vote = movieVote
        binding.popularity = moviePopularity
        binding.overview = movieOverview
    }

    private fun setupObserver() {
        movieDetailsViewModel.getMovieScreenshot("0e38a60e774102820d79ca4d9f27fe64", movieId.toString())
        movieDetailsViewModel.onlineMovieScreenshot.observe(this, Observer { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        movieDetailsViewModel.insertMovieScreenShot(it, movieId.toString())
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        movieDetailsViewModel.localMovieScreenShot.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val localMovieScreenShot = it.data
                    localMovieScreenShot?.let {
                        adapterScreenShot = AdapterScreenShot(localMovieScreenShot, this)
                        binding.rcvScreenShot.layoutManager = LinearLayoutManager(
                            this, LinearLayoutManager.HORIZONTAL, false
                        )
                        binding.rcvScreenShot.adapter = adapterScreenShot
                        binding.rcvScreenShot.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        movieDetailsViewModel.getMovieCast(movieId.toString(), "0e38a60e774102820d79ca4d9f27fe64")
        movieDetailsViewModel.onlineMovieCast.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val castList = it.data?.cast
                    castList?.let {
                        movieDetailsViewModel.insertMovieCast(castList, movieId)
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        movieDetailsViewModel.localMovieCast.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val castList = it.data
                    castList?.let {
                        adapterCast = AdapterCast(castList, this)
                        binding.rcvCast.adapter = adapterCast
                        binding.rcvCast.layoutManager = LinearLayoutManager(
                            this, LinearLayoutManager.HORIZONTAL, false
                        )
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}