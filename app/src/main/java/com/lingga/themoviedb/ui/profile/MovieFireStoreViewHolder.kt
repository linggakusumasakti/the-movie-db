package com.lingga.themoviedb.ui.profile

import androidx.recyclerview.widget.RecyclerView
import com.domain.model.Movie
import com.lingga.themoviedb.databinding.ItemListMovieFireStoreBinding

class MovieFireStoreViewHolder(private val binding: ItemListMovieFireStoreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.item = movie
        binding.executePendingBindings()
    }
}