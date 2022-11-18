package com.uli.rickandmortytest.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.uli.rickandmortytest.R

@BindingAdapter("imageUrl")
fun downloadImage(imageView: ImageView, url: String?) {

    url?.let {
        imageView.load(url) {
            crossfade(true)
                .error(R.drawable.ic_baseline_error_24)
                .placeholder(R.drawable.animation_loading)
        }

    }
}
