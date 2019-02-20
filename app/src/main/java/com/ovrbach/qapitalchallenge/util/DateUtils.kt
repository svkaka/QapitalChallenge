package com.ovrbach.qapitalchallenge.util

import java.text.SimpleDateFormat
import java.util.*

const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
const val SIMPLE_DATE_FORMAT = "dd/MM/yyyy"

fun String.parseTime(): Date = SimpleDateFormat(SERVER_DATE_FORMAT).parse(this)
fun Date.format(): String = SimpleDateFormat(SIMPLE_DATE_FORMAT).format(this) ?: ""