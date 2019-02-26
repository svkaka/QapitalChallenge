package com.ovrbach.qapitalchallenge.features.detail

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.ovrbach.qapitalchallenge.TestUtil
import com.ovrbach.qapitalchallenge.common.base.Result
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var context: Application

    @Mock
    lateinit var observer: Observer<Result<*>>
    @Mock
    private lateinit var sumObserver: Observer<Float>

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        MockitoAnnotations.initMocks(this)

        viewModel = DetailViewModel(context)
    }


    @Test
    fun getFeedMutableData() {
        viewModel.feedMutableData.observeForever(observer)
        viewModel.lastWeekSum.observeForever(sumObserver)

        viewModel.loadFeed(1)
        Mockito.verify(observer).onChanged(Result.Waiting)

        val success =
            Result.Success(listOf(TestUtil.feedWalkHostelRecent(), TestUtil.feedWalkHostelRecent()))
        viewModel.feedMutableData.postValue(success)
        Mockito.verify(observer).onChanged(success)

        val failure = Result.Error(Exception("Test exception"))
        viewModel.feedMutableData.postValue(failure)
        Mockito.verify(observer).onChanged(failure)
    }

    @Test
    fun getLastWeekSum() {
    }

}