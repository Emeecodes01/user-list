package com.mobigods.userlist.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl")
fun bindImageViewWithImage(imageView: CircleImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}