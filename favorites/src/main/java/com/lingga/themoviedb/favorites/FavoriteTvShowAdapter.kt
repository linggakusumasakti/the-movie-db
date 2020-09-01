package com.lingga.themoviedb.favorites

import androidx.recyclerview.widget.DiffUtil
import com.domain.model.TvShow
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BasePagedListAdapter

class FavoriteTvShowAdapter(onClick: (TvShow) -> Unit) :
    BasePagedListAdapter<TvShow>(diffUtil = DIFF_CALLBACK, onClick = onClick) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_tv_show

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}