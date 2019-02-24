package com.ovrbach.qapitalchallenge.common.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.base.UserId
import java.io.Serializable
import java.util.*

@Entity
open class Goal(
    var goalImageURL: String,
    var userId: UserId,
    var targetAmount: Float? = null,
    var currentBalance: Float = 0f,
    var status: String,
    var name: String,
    @PrimaryKey var id: GoalId
) : Serializable{

    @Ignore var connectedUsers: List<Int>? = null
    @Ignore var created: Array<String> = emptyArray()

    override fun toString(): String {
        return "Goal(goalImageURL='$goalImageURL', userId=$userId, targetAmount=$targetAmount, currentBalance=$currentBalance, created=${Arrays.toString(
            created
        )}, status='$status', name='$name', id=$id, connectedUsers=$connectedUsers)"
    }
}