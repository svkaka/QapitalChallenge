package com.ovrbach.qapitalchallenge.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ovrbach.qapitalchallenge.common.base.Result
import com.ovrbach.qapitalchallenge.common.entity.Goal
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
class GoalsDatabaseTests {
    private lateinit var goalsDb: GoalDao
    private lateinit var db: AppDatabase
    private val testScheduler = TestScheduler()

    private val goalA: Goal = TestUtil.goalJpgImageHalfCompleteActiveArmor1()
    private val goalB: Goal = TestUtil.goalJpgImageHalfCompleteActiveBrewery2()
    private val goalC: Goal = TestUtil.goalJpgImageHalfCompleteActiveCar1()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        goalsDb = db.goals()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    private fun getContent(): List<Goal> {
        lateinit var content: Result.Success<List<Goal>>
        goalsDb.getAll()
            .subscribeOnTest()
            .subscribe { goalsResponse ->
                content = Result.Success(goalsResponse)
            }
        testScheduler.triggerActions()
        return content.data
    }

    @Test
    @Throws(Exception::class)
    fun simpleWriteAndRead() {
        var inserted = false
        goalsDb.insert(goalC)
            .subscribeOnTest()
            .subscribe(
                { inserted = true },
                { inserted = false })

        testScheduler.triggerActions()
        assertTrue(inserted)

        val content = getContent()
        assertEquals(1, content.size)
        assertTrue(content[0].name == goalC.name)
    }

    @Test
    @Throws(Exception::class)
    fun databaseManipulation() {

        insertData()
        checkAllInserted(getContent())
        deleteA()
        deleteB()
        deleteC()

        insertAllAtOnce()
    }

    private fun insertData() {

        var aInserted = false
        goalsDb.insert(goalA)
            .subscribeOnTest()
            .subscribe(
                { aInserted = true },
                { aInserted = false })

        var bInserted = false
        goalsDb.insert(goalB)
            .subscribeOnTest()
            .subscribe(
                { bInserted = true },
                { bInserted = false })

        var cInserted = false
        goalsDb.insert(goalC)
            .subscribeOnTest()
            .subscribe(
                { cInserted = true },
                { cInserted = false })

        testScheduler.triggerActions()

        assertTrue(aInserted)
        assertTrue(bInserted)
        assertTrue(cInserted)
    }

    private fun checkAllInserted(content: List<Goal>) {
        //2 expected as goalA and goalC have same id
        assertEquals(2, content.size)

        assertTrue(content.any { it.name == goalA.name })
        assertTrue(content.any { it.name == goalB.name })
        assertTrue(content.none { it.name == goalC.name })
    }

    private fun deleteA() {
        var inserted = false
        goalsDb.delete(goalA.id)
            .subscribe { inserted = true }

        testScheduler.triggerActions()
        assertTrue(inserted)

        val content = getContent()
        assertTrue(content.none { it.name == goalA.name })
    }

    private fun deleteB() {
        var inserted = false
        goalsDb.delete(goalB.id)
            .subscribe { inserted = true }

        testScheduler.triggerActions()
        assertTrue(inserted)

        val content = getContent()
        assertTrue(content.none { it.name == goalB.name })
    }

    private fun deleteC() {
        var inserted = false
        goalsDb.delete(goalC.id)
            .subscribeOnTest()
            .subscribe { inserted = true }

        testScheduler.triggerActions()
        assertTrue(inserted)

        val content = getContent()
        assertTrue(content.none { it.name == goalC.name })
    }

    private fun insertAllAtOnce() {
        val all = listOf(goalA, goalB, goalC)
        var inserted = false

        goalsDb.insert(all)
            .subscribeOnTest()
            .subscribe { inserted = true }

        testScheduler.triggerActions()
        assertTrue(inserted)
        checkAllInserted(getContent())
    }

    private fun <T> Single<T>.subscribeOnTest(): Single<T> = this.subscribeOn(testScheduler)
    private fun Completable.subscribeOnTest() = this.subscribeOn(testScheduler)
}






