package com.ovrbach.qapitalchallenge.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.common.base.Result
import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.setGoalId
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@MediumTest
class FeedDatabaseTest {

    private lateinit var feedDb: FeedDao
    private lateinit var goalsDb: GoalDao
    private lateinit var db: AppDatabase
    private val testScheduler = TestScheduler()

    private val goalA = TestUtil.goalJpgImageHalfCompleteActiveArmor1()
    private val goalB = TestUtil.goalJpgImageHalfCompleteActiveArmor1()
    private val feedA = TestUtil.feedSale1()
    private val feedB = TestUtil.feedDinner1()
    private val feedC = TestUtil.feedWalk2()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        feedDb = db.feed()
        goalsDb = db.goals()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    private fun getFeed(goalId: GoalId): List<Feed> {
        lateinit var content: Result.Success<List<Feed>>
        feedDb.withGoalId(goalId)
            .subscribeOnTest()
            .subscribe { goalsResponse ->
                content = Result.Success(goalsResponse)
            }
        testScheduler.triggerActions()
        return content.data
    }


    @Test
    @Throws(Exception::class)
    fun writeGetDeleteParent() {
        var completed = false

        //insert goal
        goalsDb.insert(goalA)
            .subscribeOnTest()
            .subscribe(
                { completed = true },
                { completed = false })

        testScheduler.triggerActions()
        assertTrue(completed)

        //insert all feed items
        val feed = listOf(feedA, feedB, feedC)
        feed.setGoalId(goalA.id)

        completed = false
        feedDb.insert(feed)
            .subscribeOnTest()
            .subscribe(
                { completed = true },
                { completed = false })
        testScheduler.triggerActions()
        assertTrue(completed)

        val content = getFeed(goalA.id)
        val contentEmpty = getFeed(goalB.id)

        assertEquals(2, content.size)
        assertTrue(content.any { it.message == feedA.message })
        assertTrue(content.any { it.message == feedC.message })
        assertEquals(0, contentEmpty.size)

        //remove goalA
        completed = false
        goalsDb.delete(goalA)
            .subscribeOnTest()
            .subscribe(
                { completed = true },
                { completed = false })
        testScheduler.triggerActions()
        assertTrue(completed)

        val contentAfter = getFeed(goalA.id)
        assertEquals(0, contentAfter.size)
    }

    private fun <T> Single<T>.subscribeOnTest(): Single<T> = this.subscribeOn(testScheduler)
    private fun Completable.subscribeOnTest() = this.subscribeOn(testScheduler)
}
