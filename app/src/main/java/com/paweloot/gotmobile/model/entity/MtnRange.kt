package com.paweloot.gotmobile.model.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.moshi.Json

data class MtnRange(
    val id: Int,
    @Json(name = "nazwa_pasma") val name: String,
    val url: String
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .dontAnimate()
        .into(view)
}