package com.ovrbach.qapitalchallenge.common.logic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateUtilsKtTest {

    @Test
    fun timeAgo() {
        val now = Date(2019, 2, 18, 6, 0, 0)

        secondsAgo(now)
        minutesAgo(now)
        hoursAgo(now)
        daysAgo(now)
        weeksAgo(now)
        monthsAgo(now)
        farAgo(now)
    }

    private fun secondsAgo(now: Date) {
        //5 seconds
        val past = Date(2019, 2, 18, 5, 59, 55)
        val result = past.timeAgo(now)
        assertEquals("Just a while ago", result)
    }

    private fun minutesAgo(now: Date) {
        //2 minutes
        val past = Date(2019, 2, 18, 5, 58, 0)
        val result = past.timeAgo(now)
        assertEquals("2 minutes ago", result)
    }

    private fun hoursAgo(now: Date) {
        //3 hours
        val past = Date(2019, 2, 18, 3, 0, 0)
        val result = past.timeAgo(now)
        assertEquals("3 hours ago", result)
    }

    private fun daysAgo(now: Date) {
        //4 days
        val past = Date(2019, 2, 14, 6, 0, 0)
        val result = past.timeAgo(now)
        assertEquals("4 days ago", result)
    }

    private fun weeksAgo(now: Date) {
        //2 weeks ago
        val past = Date(2019, 2, 4, 6, 0, 0)
        val result = past.timeAgo(now)
        assertEquals("2 weeks ago", result)
    }

    private fun monthsAgo(now: Date) {
        val past = Date(2018, 12, 18, 3, 0, 0)
        val result = past.timeAgo(now)
        assertEquals("2 months ago", result)
    }

    private fun farAgo(now: Date) {
        //stupidest api in the world
        val past = Date(2018 - 1900, 2, 18, 6, 0, 0)
        val result = past.timeAgo(now)
        assertEquals("18/03/2018", result)
    }

    @Test
    fun parseTime() {

        val formattedString = "2015-03-10T14:55:16.025Z"
        val date = formattedString.parseTime()

        assertEquals(1425995716025, date.time)
        assertEquals(2, date.month)
        assertEquals(2015 - 1900, date.year)
        assertEquals(10, date.date)
        assertEquals(2, date.day)
        assertEquals(14, date.hours)
        assertEquals(55, date.minutes)
        assertEquals(16, date.seconds)
    }

    @Test
    fun format() {
        val date = Date(1425995716025)
        val formatted = date.format()
        assertEquals(formatted, "10/03/2015")
    }
}