package com.lingga.themoviedb.ui.detailmovie

import androidx.recyclerview.widget.DiffUtil
import com.domain.model.Credit
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseAdapter

class CreditAdapter : BaseAdapter<Credit>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_credit

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Credit>() {
            override fun areContentsTheSame(
                oldItem: Credit,
                newItem: Credit
            ): Boolean {
                return oldItem.castId == newItem.castId
            }

            override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
                return oldItem == newItem
            }
        }
    }
}