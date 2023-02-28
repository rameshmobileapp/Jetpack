package com.mindorks.framework.mvvm.ui.moviedetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ramesh.movie.R
import com.ramesh.movie.databinding.ItemCastBinding
import com.ramesh.movie.mvvm.data.local.model.MovieCast
import kotlinx.android.synthetic.main.item_cast.view.*


class AdapterCast(private var castList: List<MovieCast>, private val context: Context) :
    RecyclerView.Adapter<AdapterCast.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemCastBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_cast, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast : MovieCast = castList[position]
        cast.let { holder.bind(cast) }
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    inner class ViewHolder( private val binding:  ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: MovieCast) {
            binding.cast = cast
        }

    }
}