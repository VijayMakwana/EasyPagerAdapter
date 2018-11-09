package com.easypageradapter.easypageradaptersample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("imageDrawable")
fun setImageDrawable(imageView: ImageView, imageDrawable: Int) {
    imageView.setImageResource(imageDrawable)
}