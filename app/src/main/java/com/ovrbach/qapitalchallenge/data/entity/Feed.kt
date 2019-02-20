package com.ovrbach.qapitalchallenge.data.entity

import com.ovrbach.qapitalchallenge.util.parseTime

class Feed(
    val id: String,
    val type: String,
    val timestamp: String,
    val message: String,
    val amount: Float,
    val userId: Int
//    todo savingsRuleId
) {
    fun date() = timestamp.parseTime()
}