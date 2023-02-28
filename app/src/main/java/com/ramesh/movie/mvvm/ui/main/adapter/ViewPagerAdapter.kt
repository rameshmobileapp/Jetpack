package com.mindorks.framework.mvvm.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.ramesh.movie.R
import com.ramesh.movie.databinding.ItemLayoutBinding
import com.ramesh.movie.mvvm.data.local.model.MovieList

class ViewPagerAdapter(private val context: Context, private val results: List<MovieList>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding : ItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.item_layout, container, false)
        val movieList = results[position]
        binding.movie = movieList
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int {
        return results.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}
