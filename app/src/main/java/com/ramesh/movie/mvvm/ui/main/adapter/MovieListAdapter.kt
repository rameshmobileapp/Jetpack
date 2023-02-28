package com.ramesh.movie.mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramesh.movie.R
import com.ramesh.movie.mvvm.data.remote.model.movielist.Results
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieListAdapter(
    private var results: List<Results>
) : RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {

//    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
    inner class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(results: Results) {
            Glide.with(itemView.imageViewAvatar.context)
                .load("https://image.tmdb.org/t/p/w500/".plus(results.poster_path))
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_layout, parent, false)

        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(this.results[position])


}