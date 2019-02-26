package com.ovrbach.qapitalchallenge.common.logic

import java.text.SimpleDateFormat
import java.util.*


const val MILLIS_IN_SECOND: Long = 1000L
const val MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60
const val MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60
const val MILLIS_IN_DAY = MILLIS_IN_HOUR * 24
const val MILLIS_IN_WEEK = MILLIS_IN_DAY * 7
const val MILLIS_IN_MONTH = MILLIS_IN_WEEK * 4
const val MILLIS_IN_3_MONTHS = MILLIS_IN_MONTH * 3

fun Date.timeAgo() = this.timeAgo(Date(System.currentTimeMillis()))

fun Date.timeAgo(now: Date): String {
    val previous = this

    val difference = now.time - previous.time
    if (difference > MILLIS_IN_3_MONTHS) {
        return previous.format()
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


const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
const val SIMPLE_DATE_FORMAT = "dd/MM/yyyy"

fun String.parseTime(): Date = SimpleDateFormat(SERVER_DATE_FORMAT).parse(this)
fun Date.format(): String = SimpleDateFormat(SIMPLE_DATE_FORMAT).format(this) ?: ""
fun Date.formatToServer(): String = SimpleDateFormat(SERVER_DATE_FORMAT).format(this) ?: ""