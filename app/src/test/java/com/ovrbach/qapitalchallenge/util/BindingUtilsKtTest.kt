package com.ovrbach.qapitalchallenge.util

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BindingUtilsKtTest {

    @Before
    fun setUp() {
    }

    @Test
    fun getProgress() {
        var progress = getProgress(20f, 100f)
        assertEquals(20, progress)
        progress = getProgress(20f, 500f)
        assertEquals(4, progress)
        progress = getProgress(-10f, 200f)
        assertEquals(-5, progress)
        progress = getProgress(480f, 120f)
        assertEquals(400, progress)
    }
}