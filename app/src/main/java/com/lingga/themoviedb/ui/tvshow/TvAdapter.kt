package com.lingga.themoviedb.ui.tvshow

import androidx.recyclerview.widget.DiffUtil
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.ui.BaseAdapter

class TvAdapter(onClick: (TvShow) -> Unit) :
    BaseAdapter<TvShow>(diffUtil = DIFF_CALLBACK, onClick = onClick) {

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