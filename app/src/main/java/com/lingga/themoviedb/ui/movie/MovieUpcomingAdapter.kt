package com.lingga.themoviedb.ui.movie

import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseAdapterHome

class MovieUpcomingAdapter(onClick: (Movie) -> Unit) :
    BaseAdapterHome<Movie>(onClick = onClick) {
    override val getLayoutIdRes: Int
        get() = R.layout.item_list_movie_home
}