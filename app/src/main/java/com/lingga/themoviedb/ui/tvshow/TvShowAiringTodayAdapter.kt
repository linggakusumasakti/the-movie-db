package com.lingga.themoviedb.ui.tvshow

import com.domain.model.TvShow
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseAdapterHome

class TvShowAiringTodayAdapter(onClick: (TvShow) -> Unit) : BaseAdapterHome<TvShow>(onClick) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_tv_show_home
}