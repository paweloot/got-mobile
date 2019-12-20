package com.paweloot.gotmobile.model.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class MtnRange(
    val id: Int,
    val name: String,
    val url: String
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .dontAnimate()
        .into(view)
}