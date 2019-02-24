package com.ovrbach.qapitalchallenge.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.Goal
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FeedDao {

    @Query("SELECT * FROM feed WHERE goalId == :goalId")
    fun getForGoalId(goalId: GoalId): Single<List<Feed>>

    @Insert
    fun insert(feed: Feed): Single<Long>

    @Insert
    fun insert(goal: List<Feed>): Completable
}