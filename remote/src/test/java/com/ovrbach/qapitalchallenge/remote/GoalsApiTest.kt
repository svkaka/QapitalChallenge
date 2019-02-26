package com.ovrbach.qapitalchallenge.remote

import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.remote.request.FeedService
import com.ovrbach.qapitalchallenge.remote.request.GoalsService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*


class GoalsApiTest {
    lateinit var goalsService: GoalsService
    lateinit var feedService: FeedService

    @Before
    @Throws
    fun setUp() {
        val config = ApiConfig.Builder().apply {
            customInterceptor = UnitTestingHttpInterceptor()
        }.build()

        val goalsApi = GoalsApi.getInstance(config = config)
        goalsService = goalsApi.goalService
        feedService = goalsApi.feedService
    }

    @Test
    fun getGoals() {
        lateinit var response: List<Goal>
        goalsService
            .getSavedGoals()
            .subscribeOnTest()
            .subscribe { goals ->
                response = goals.savingsGoals
            }
        testScheduler.triggerActions()

        assertEquals(8, response.size)
        assertEquals(
            "https://static.qapitalapp.net/assets/ios-staging.api.qapital.com/images/goal/6dc5befb-5389-4d89-ab37-205c83ccf79c.jpg",
            response[0].goalImageURL
        )
        assertEquals(1, response[1].userId)
        assertEquals(500f, response[2].targetAmount)
        assertEquals(500f, response[2].currentBalance)
        assertEquals(arrayOf("2014", "12", "5"), response[3].created)
        assertEquals("active", response[4].status)
        assertEquals("Brand New Wheels", response[5].name)
        assertEquals(7, response[6].id)

        //todo
        //assertEquals(arrayOf(5, 6), response[6].connectedUsers)
    }

    @Test
    fun getFeedForGoal1() {
        lateinit var response: List<Feed>
        val goalId = 1
        feedService
            .getFeed(goalId)
            .subscribeOnTest()
            .subscribe { feed ->
                response = feed.feed
            }
        testScheduler.triggerActions()

        assertEquals("9d2d3dcd-2b0e-456b-b398-36d42e22bb77", response[0].id)
        assertEquals(4f, response[1].amount)
        assertEquals("2015-03-09T12:31:16.025Z", response[2].timestamp)
        assertEquals("<strong>Johan</strong> made a roundup.", response[3].message)
        assertEquals("saving", response[4].type)
        assertEquals(-1, response[5].goalId) //default value
        assertEquals(1, response[6].userId)
        assertEquals(Date(1425530035025), response[7].date)

        response[5].goalId = goalId
        assertEquals(goalId, response[5].goalId)
    }

    private val testScheduler = TestScheduler()
    private fun <T> Single<T>.subscribeOnTest(): Single<T> = this.subscribeOn(testScheduler)
    private fun Completable.subscribeOnTest() = this.subscribeOn(testScheduler)

}