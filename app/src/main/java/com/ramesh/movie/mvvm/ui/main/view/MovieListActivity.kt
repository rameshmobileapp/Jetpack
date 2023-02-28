package com.mindorks.framework.mvvm.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.mindorks.framework.mvvm.ui.main.adapter.ViewPagerAdapter
import com.ramesh.movie.R
import com.ramesh.movie.databinding.ActivityMovieListBinding
import com.ramesh.movie.mvvm.data.local.model.MovieList
import com.ramesh.movie.mvvm.ui.base.BaseActivity
import com.ramesh.movie.mvvm.ui.main.viewmodel.MainViewModel
import com.ramesh.movie.mvvm.ui.moviedetails.view.MovieDetailsActivity
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_ID
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_NAME
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_OVER_VIEW
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_POPULARITY
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_POSTER
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_RATE
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_STILL
import com.ramesh.movie.mvvm.utils.Constant.MOVIE_VOTE
import com.ramesh.movie.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMovieListBinding

    private var movieName: String? = null
    private var movieRate: String? = null
    private var movieVote: String? = null
    private var moviePopularity: String? = null
    private var movieOverview: String? = null
    private var movieStill: String? = null
    private var moviePoster: String? = null
    private var movieId: Int = 0

    private lateinit var localModelList: List<MovieList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewBinding()
        setUpDataBinding()
        setupUI()
        setupObserver()
    }

    override fun setUpViewBinding() {
    }

    override fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
    }

    private fun setupUI() {
        binding.imgViewDetail.setOnClickListener {
            val moveToMovieDetails = Intent(this, MovieDetailsActivity::class.java)
            moveToMovieDetails.putExtra(MOVIE_OVER_VIEW, movieOverview)
            moveToMovieDetails.putExtra(MOVIE_STILL, movieStill)
            moveToMovieDetails.putExtra(MOVIE_POSTER, moviePoster)
            moveToMovieDetails.putExtra(MOVIE_ID, movieId)
            moveToMovieDetails.putExtra(MOVIE_RATE, movieRate)
            moveToMovieDetails.putExtra(MOVIE_VOTE, movieVote)
            moveToMovieDetails.putExtra(MOVIE_POPULARITY, moviePopularity)
            moveToMovieDetails.putExtra(MOVIE_NAME, movieName)
            startActivity(moveToMovieDetails)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getMovieList("0e38a60e774102820d79ca4d9f27fe64")
    }

    private fun setupObserver() {
        mainViewModel.movieList.observe(this, Observer { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let {
                        mainViewModel.insertMovieList(it)
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.localMovieList.observe(this) { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        localModelList = it
                        Log.d("localModelList", ">>>>>>>>>>>><<<<<<<<<<: "+localModelList.size)
                        val viewPagerAdapter = ViewPagerAdapter(this, localModelList)
                        binding.recyclerView.adapter = viewPagerAdapter
                        setMovieDetails(0)
                        binding.recyclerView.addOnPageChangeListener(object :
                            OnPageChangeListener {
                            override fun onPageScrollStateChanged(state: Int) {}
                            override fun onPageScrolled(
                                position: Int, positionOffset: Float, positionOffsetPixels: Int
                            ) {
                            }

                            override fun onPageSelected(position: Int) {
                                setMovieDetails(position)
                            }
                        })
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }


    fun setMovieDetails(position: Int) {

        val movieDetails = localModelList[position]
        binding.movielist = movieDetails

        movieDetails.let {
            movieName = it.moviename
            binding.tvMovieTittle2.startAnimation(fadeInAnimation())

            movieRate = it.movierate.split(".")[0]
            binding.tvMovieRate.startAnimation(fadeInAnimation())

            movieVote = it.moviecount
            binding.tvMovieVote.startAnimation(fadeInAnimation())

            moviePopularity = it.moviepopularity.split(".")[0]
            binding.tvMoviePopular.startAnimation(fadeInAnimation())

            movieOverview = it.movieoverview
            moviePoster = it.movieposter
            movieStill = it.moviestill
            movieId = it.movieid.toInt()
        }
    }

}
