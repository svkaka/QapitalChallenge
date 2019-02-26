package com.ovrbach.qapitalchallenge.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.entity.Feed
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FeedDao {

    @Query("SELECT * FROM feed WHERE goalId == :goalId")
    fun withGoalId(goalId: GoalId): Single<List<Feed>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(feed: Feed): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(feed: List<Feed>): Completable
}