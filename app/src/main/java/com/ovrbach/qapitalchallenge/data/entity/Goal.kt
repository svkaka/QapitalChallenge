package com.ovrbach.qapitalchallenge.data.entity

import com.ovrbach.qapitalchallenge.data.base.GoalId
import com.ovrbach.qapitalchallenge.data.base.UserId
import java.io.Serializable
import java.util.*

class Goal(
    val goalImageURL: String,
    val userId: UserId,
    val targetAmount: Float? = null,
    val currentBalance: Float = 0f,
    val created: Array<String>,
    val status: String,
    val name: String,
    val id: GoalId,
    val connectedUsers: List<Int>?
) : Serializable{

    override fun toString(): String {
        return "Goal(goalImageURL='$goalImageURL', userId=$userId, targetAmount=$targetAmount, currentBalance=$currentBalance, created=${Arrays.toString(
            created
        )}, status='$status', name='$name', id=$id, connectedUsers=$connectedUsers)"
    }
}