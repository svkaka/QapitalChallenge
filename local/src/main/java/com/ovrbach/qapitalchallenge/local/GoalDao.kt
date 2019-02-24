package com.ovrbach.qapitalchallenge.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovrbach.qapitalchallenge.common.entity.Goal
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GoalDao {

    @Query("SELECT * from goal")
    fun getAll(): Single<List<Goal>>

    @Insert
    fun insert(goal: Goal): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: List<Goal>): Completable

}