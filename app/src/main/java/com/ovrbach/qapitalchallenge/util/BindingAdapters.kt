package com.ovrbach.qapitalchallenge.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ovrbach.qapitalchallenge.common.base.Result

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("makeVisible")
fun View.makeVisible(show: Boolean) {
    if (show) {
        this.show()
    } else {
        this.hide()
    }
}

@BindingAdapter("progressVisibility")
fun View.progressVisibility(any: Result<Any>) {
    if (any is Result.Waiting) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("contentVisibility")
fun View.contentVisibility(any: Result<Any>) {
    if (any is Result.Success) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("errorVisibility")
fun View.errorVisibility(any: Result<Any>) {
    if (any is Result.Error) {
        show()
    } else {
        hide()
    }
}