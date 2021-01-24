package com.mobigods.userlist.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl")
fun bindImageViewWithImage(imageView: CircleImageView, url: String?) {
    url?.let {
        Glide.with(imageView).load(it).into(imageView)
    }
}
