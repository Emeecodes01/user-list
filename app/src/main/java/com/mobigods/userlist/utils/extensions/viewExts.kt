package com.mobigods.userlist.utils.extensions

import android.view.View


fun View.onClick(action: () -> Unit) {
    setOnClickListener {
        action.invoke()
    }
}