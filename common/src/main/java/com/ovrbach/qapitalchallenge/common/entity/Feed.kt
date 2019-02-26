package com.ovrbach.qapitalchallenge.common.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovrbach.qapitalchallenge.common.base.FeedId
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.logic.parseTime
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(
    foreignKeys = [ForeignKey(
        entity = Goal::class,
        childColumns = arrayOf("goalId"),
        parentColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
open class Feed(
    @PrimaryKey var id: FeedId,
    var type: String,
    var timestamp: String,
    var message: String,
    var amount: Float,
    var userId: Int
) {
    @Ignore
    val date = timestamp.parseTime()
    var goalId: GoalId = -1
}

fun List<Feed>.setGoalId(id: GoalId) {
    this.forEach { it.goalId = id }
}