package com.brownik.unsplash.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.brownik.unsplash.R
import com.bumptech.glide.Glide

object PhotoDataBindingAdapter {
    @BindingAdapter("app:setImageResource")
    @JvmStatic
    fun setImageResource(targetView: ImageView, url: String) {
        Glide.with(targetView.context)
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .thumbnail(0.1f)
            .centerCrop()
            .into(targetView)
    }
}