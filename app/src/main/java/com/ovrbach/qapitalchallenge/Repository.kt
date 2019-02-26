package com.ovrbach.qapitalchallenge

import android.content.Context
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.base.Response
import com.ovrbach.qapitalchallenge.common.base.Source
import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.local.AppDatabase
import com.ovrbach.qapitalchallenge.remote.GoalsApi
import com.ovrbach.qapitalchallenge.util.subscribeOnNewObserveOnMain
import io.reactivex.Flowable
import io.reactivex.Single

//todo dagger

class Repository private constructor(
    private val database: AppDatabase,
    private val remote: GoalsApi
) {

    companion object {
        @Volatile
        var INSTANCE: Repository? = null

        fun getInstance(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = buildRepo(context)
                INSTANCE = instance
                instance
            }
        }

        private fun buildRepo(context: Context): Repository {
            val database = AppDatabase.getDatabase(context)
            val remoteApi = GoalsApi.getInstance()
            return Repository(database, remoteApi)
        }
    }

    fun getGoals(): Flowable<Response<List<Goal>>> {
        val local = database.goals().getAll()
            .map { Response(Source.LOCAL, it) }
        val remote = remote.goalService.getSavedGoals()
            .map { Response(Source.REMOTE, it.savingsGoals) }
            .onErrorReturn { Response(Source.REMOTE, null) }

        return Single.concat(local, remote)
    }

    fun getFeedForGoal(id: GoalId): Flowable<Response<List<Feed>>> {
        val local = database.feed().withGoalId(id)
            .map { Response(Source.LOCAL, it) }
        val remote = remote.feedService.getFeed(id)
            .map { Response(Source.REMOTE, it.feed) }
            .onErrorReturn { Response(Source.REMOTE, null) }

        return Single.concat(local, remote)
    }

    fun updateGoalsDatabase(goals: List<Goal>) {
        database.goals().insert(goals)
            .subscribeOnNewObserveOnMain()
            .subscribe()
    }

    fun updateFeedDatabase(feed: List<Feed>) {
        database.feed().insert(feed)
            .subscribeOnNewObserveOnMain()
            .subscribe()
    }
}
