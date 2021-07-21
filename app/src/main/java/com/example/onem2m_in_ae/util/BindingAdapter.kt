package com.example.onem2m_in_ae.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

@BindingAdapter("bind:test")
fun setPhotoId(
    imageView: ImageView,
    containerId: Int,
){
    Glide.with(imageView.context)
        .load(containerId)
        .into(imageView)
}

