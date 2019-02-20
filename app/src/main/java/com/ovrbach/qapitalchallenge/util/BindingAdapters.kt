package com.ovrbach.qapitalchallenge.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ovrbach.qapitalchallenge.data.base.Request

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
fun View.progressVisibility(any: Request<Any>) {
    if (any is Request.Waiting) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("contentVisibility")
fun View.contentVisibility(any: Request<Any>) {
    if (any is Request.Success) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("errorVisibility")
fun View.errorVisibility(any: Request<Any>) {
    if (any is Request.Error) {
        show()
    } else {
        hide()
    }
}