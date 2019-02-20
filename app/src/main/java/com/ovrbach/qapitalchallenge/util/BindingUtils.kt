package com.ovrbach.qapitalchallenge.util

import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_DIV
import java.util.*


const val MILLIS_IN_SECOND: Long = 1000L
const val MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60
const val MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60
const val MILLIS_IN_DAY = MILLIS_IN_HOUR * 60
const val MILLIS_IN_WEEK = MILLIS_IN_DAY * 7
const val MILLIS_IN_MONTH = MILLIS_IN_WEEK * 4
const val MILLIS_IN_3_MONTHS = MILLIS_IN_MONTH * 3


fun Date.getBestDescribingString(): String {
    val now = Date(System.currentTimeMillis())
    val previous = this

    val difference = now.time - previous.time

    if (difference > MILLIS_IN_3_MONTHS) {
        return now.format()
    }

    if (difference > MILLIS_IN_MONTH) {
        val count = difference / MILLIS_IN_MONTH
        return "$count months ago"
    }

    if (difference > MILLIS_IN_WEEK) {
        val count = difference / MILLIS_IN_WEEK
        return "$count weeks ago"
    }

    if (difference > MILLIS_IN_DAY) {
        val count = difference / MILLIS_IN_DAY
        return "$count days ago"
    }

    if (difference > MILLIS_IN_HOUR) {
        val count = difference / MILLIS_IN_HOUR
        return "$count hours ago"
    }

    if (difference > MILLIS_IN_MINUTE) {
        val count = difference / MILLIS_IN_MINUTE
        return "$count minutes ago"
    }

    return "Just a while ago"
}

fun getProgress(current: Float, top: Float?): Int = if (top == null) 0 else (100 * current / top).toInt()

fun String.toSpanned(): Spanned? = HtmlCompat.fromHtml(this, FROM_HTML_MODE_COMPACT)