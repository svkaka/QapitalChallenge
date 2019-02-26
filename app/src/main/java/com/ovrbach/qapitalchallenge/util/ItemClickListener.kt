package com.ovrbach.qapitalchallenge.util

import android.view.View

interface ItemClickListener<T> {
    fun onItemClicked(item: T)
}