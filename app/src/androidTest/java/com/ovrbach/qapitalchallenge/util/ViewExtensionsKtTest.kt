package com.ovrbach.qapitalchallenge.util

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ovrbach.qapitalchallenge.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewExtensionsKtTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun hide() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)
        view.makeVisible(show = true)
        assertEquals(View.VISIBLE, view.visibility)

        view.hide()
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun show() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)
        view.makeVisible(show = false)
        assertEquals(View.GONE, view.visibility)

        view.show()
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun inflate() {
        val viewGroup = FrameLayout(context)
        val root = viewGroup.inflate(R.layout.item_feed)
        val textView = root.findViewById<View>(R.id.textView2)

        assertTrue(textView != null)
    }

    @Test
    fun bind() {
        //todo
//        val viewGroup = FrameLayout(context)
//
//        val binding = viewGroup.bind<ItemFeedBinding>(R.layout.item_feed)
//        val textView = binding.root.findViewById<View>(R.id.textView2)
//
//        val feed = TestUtil.feedWalk2()
//        binding.item = feed
    }

    @Test
    fun inflater() {
        val viewGroup = FrameLayout(context)
        val inflater = viewGroup.inflater()

        val root = inflater.inflate(R.layout.item_feed, viewGroup, false)
        val textView = root.findViewById<View>(R.id.textView2)
        assertTrue(textView != null)
    }

}