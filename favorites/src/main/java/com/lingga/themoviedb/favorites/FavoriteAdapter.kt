package com.lingga.themoviedb.favorites

import androidx.recyclerview.widget.DiffUtil
import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BasePagedListAdapter

class FavoriteAdapter(onClick: (Movie) -> Unit) :
    BasePagedListAdapter<Movie>(diffUtil = DIFF_CALLBACK, onClick = onClick) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_movie

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}