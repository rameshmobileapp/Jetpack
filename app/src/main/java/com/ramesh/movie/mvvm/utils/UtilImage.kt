package com.ramesh.movie.mvvm.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class UtilImage {


    companion object {
        @JvmStatic
        @BindingAdapter("android:loadCastImage")
        fun loadCastImage(view: AppCompatImageView, url: String) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500"+url).into(view)
        }

        @JvmStatic
        @BindingAdapter("android:loadScreenShotImage")
        fun loadScreenShotImage(view: AppCompatImageView, url: String) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500"+url).into(view)
        }
        @JvmStatic
        @BindingAdapter("android:loadPoster")
        fun loadPoster(view: AppCompatImageView, url: String) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500/"+url).into(view)
        }

        @JvmStatic
        @BindingAdapter("android:loadStillImage")
        fun loadStillImage(view: AppCompatImageView, url: String) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500/"+url).into(view)
        }

        @JvmStatic
        @BindingAdapter("android:loadMoviePost")
        fun loadMoviePost(view: AppCompatImageView, url: String) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500/"+url).into(view)
        }

    }
}