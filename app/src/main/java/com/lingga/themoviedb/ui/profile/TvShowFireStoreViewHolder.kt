package com.lingga.themoviedb.ui.profile

import androidx.recyclerview.widget.RecyclerView
import com.domain.model.TvShow
import com.lingga.themoviedb.databinding.ItemListTvShowFireStoreBinding

class TvShowFireStoreViewHolder(private val binding: ItemListTvShowFireStoreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TvShow) {
        binding.item = tvShow
        binding.executePendingBindings()
    }
}