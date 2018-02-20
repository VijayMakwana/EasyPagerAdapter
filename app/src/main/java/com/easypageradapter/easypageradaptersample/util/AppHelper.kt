package com.easypageradapter.easypageradaptersample.util

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView


@BindingAdapter("imageDrawable")
fun setImageDrawable(imageView: ImageView, imageDrawable: Int) {
    imageView.setImageResource(imageDrawable)
}