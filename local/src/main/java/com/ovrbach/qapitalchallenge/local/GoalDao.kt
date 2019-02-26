package com.ovrbach.qapitalchallenge.local

import androidx.room.*
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.entity.Goal
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GoalDao {

    @Query("SELECT * from goal")
    fun getAll(): Single<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: Goal): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: List<Goal>): Completable

    @Delete
    fun delete(goalId: Goal): Completable

    @Query("DELETE FROM goal WHERE id = :id")
    fun delete(id: GoalId): Completable

}