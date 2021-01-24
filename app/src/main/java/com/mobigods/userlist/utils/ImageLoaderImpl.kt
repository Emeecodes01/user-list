package com.mobigods.userlist.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(@ApplicationContext private val context: Context) : ImageLoader {

    override fun loadImage(image: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .into(image)
    }
}
