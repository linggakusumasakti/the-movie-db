package com.lingga.themoviedb.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseAdapter

class MovieNowPlayingAdapter(onClick: (Movie) -> Unit) :
    BaseAdapter<Movie>(onClick = onClick, diffUtil = DIFF_CALLBACK) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_movie_home

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = false

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}