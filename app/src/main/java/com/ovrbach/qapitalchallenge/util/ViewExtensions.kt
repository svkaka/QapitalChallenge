package com.ovrbach.qapitalchallenge.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun <T : ViewDataBinding> ViewGroup.bind(@LayoutRes layoutRes: Int): T =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, false)

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

fun <T : ViewDataBinding> LayoutInflater.bind(container: ViewGroup?, @LayoutRes layoutRes: Int): T =
    DataBindingUtil.inflate(this, layoutRes, container, false)
