package com.lingga.themoviedb.ui.detailmovie

import androidx.recyclerview.widget.DiffUtil
import com.data.source.remote.response.GenreResponse
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseAdapter

class GenreAdapter : BaseAdapter<GenreResponse>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_genre

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenreResponse>() {
            override fun areContentsTheSame(
                oldItem: GenreResponse,
                newItem: GenreResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: GenreResponse, newItem: GenreResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}