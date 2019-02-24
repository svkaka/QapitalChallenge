package com.ovrbach.qapitalchallenge.util

import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_DIV
import java.util.*

fun getProgress(current: Float, top: Float?): Int = if (top == null) 0 else (100 * current / top).toInt()

fun String.toSpanned(): Spanned? = HtmlCompat.fromHtml(this, FROM_HTML_MODE_COMPACT)