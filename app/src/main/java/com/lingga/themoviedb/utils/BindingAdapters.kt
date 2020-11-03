package com.lingga.themoviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.lingga.themoviedb.R

@BindingAdapter("imageMovie")
fun bindImageMovie(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load("https://image.tmdb.org/t/p/w500/$imgUrl")
            .into(imgView)
    }
}

@BindingAdapter("imageCast")
fun bindImageCast(imgView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        Glide.with(imgView.context)
            .load("https://image.tmdb.org/t/p/w500/$imgUrl")
            .into(imgView)
    } else {
        Glide.with(imgView.context)
            .load(R.drawable.ic_baseline_person_24)
            .into(imgView)
    }
}