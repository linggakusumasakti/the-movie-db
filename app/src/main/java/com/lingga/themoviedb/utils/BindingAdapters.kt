package com.lingga.themoviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageMovie")
fun bindImageMovie(imgView: ImageView, imgUrl: String?) {
  imgUrl?.let {
    Glide.with(imgView.context)
      .load("https://image.tmdb.org/t/p/w500/$imgUrl")
      .into(imgView)
  }
}