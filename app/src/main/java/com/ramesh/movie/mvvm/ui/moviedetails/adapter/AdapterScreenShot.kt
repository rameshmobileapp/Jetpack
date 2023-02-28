package com.mindorks.framework.mvvm.ui.moviedetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ramesh.movie.R
import com.ramesh.movie.databinding.ItemScreenshotBinding
import com.ramesh.movie.mvvm.data.local.model.MovieScreenShot

class AdapterScreenShot(private var screenShotList: List<MovieScreenShot>, private val context: Context) :
    RecyclerView.Adapter<AdapterScreenShot.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemScreenshotBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_screenshot, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val screenShot = screenShotList[position]
        screenShot.let {
            holder.bind(screenShot)
        }
    }

    override fun getItemCount(): Int {
        return screenShotList.size
    }

    inner class ViewHolder(private val binding: ItemScreenshotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(screenShot: MovieScreenShot) {
            binding.screenshot = screenShot
        }
    }
}