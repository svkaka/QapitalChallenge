package com.ovrbach.qapitalchallenge.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ovrbach.qapitalchallenge.common.base.Result
import com.ovrbach.qapitalchallenge.test.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
@MediumTest
class BindingAdaptersKtTest {

    private lateinit var context: Context
    private val callback: DataFetcher.DataCallback<InputStream>? = null

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun loadImage() {
        //todo
    }

    @Test
    fun makeVisible() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)
        view.makeVisible(show = false)
        assertEquals(View.GONE, view.visibility)
        view.makeVisible(show = true)
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun progressVisibility() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)

        var response: Result<*> = Result.Error(Exception("Test exception"))
        view.progressVisibility(response)
        assertEquals(View.GONE, view.visibility)

        response = Result.Waiting
        view.progressVisibility(response)
        assertEquals(View.VISIBLE, view.visibility)

        response = Result.Success(Any())
        view.progressVisibility(response)
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun contentVisibility() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)

        var response: Result<*> = Result.Error(Exception("Test exception"))
        view.contentVisibility(response)
        assertEquals(View.GONE, view.visibility)

        response = Result.Success(Any())
        view.contentVisibility(response)
        assertEquals(View.VISIBLE, view.visibility)

        response = Result.Waiting
        view.contentVisibility(response)
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun errorVisibility() {
        val view = View(context)
        assertEquals(View.VISIBLE, view.visibility)

        var response: Result<*> = Result.Success(Any())
        view.errorVisibility(response)
        assertEquals(View.GONE, view.visibility)

        response = Result.Error(Exception("Test exception"))
        view.errorVisibility(response)
        assertEquals(View.VISIBLE, view.visibility)

        response = Result.Waiting
        view.errorVisibility(response)
        assertEquals(View.GONE, view.visibility)
    }
}