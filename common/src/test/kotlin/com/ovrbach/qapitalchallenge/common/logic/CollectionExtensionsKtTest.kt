package com.ovrbach.qapitalchallenge.common.logic

import org.junit.Assert.assertEquals
import org.junit.Test

class CollectionExtensionsKtTest {

    @Test
    fun sumBy() {
        val valuesNormal: List<Float> = listOf(15.0f, 12.0f, 13.0f, -4.0f)
        val resultNormal: Float = valuesNormal.sumByFloat { it }

        assertEquals(36.0f, resultNormal)
    }

}