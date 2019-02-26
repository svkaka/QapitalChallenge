package com.ovrbach.qapitalchallenge.features

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
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var context: Application

    @Mock
    lateinit var observer: Observer<Result<*>>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel(context)
    }

    @Test
    fun getGoalsLiveData() {
        viewModel.goalsLiveData.observeForever(observer)

        viewModel.fetchGoals()
        verify(observer).onChanged(Result.Waiting)

        val success = Result.Success(listOf(TestUtil.goalJpgImageHalfCompleteActiveArmor1()))
        viewModel.goalsLiveData.postValue(success)
        verify(observer).onChanged(success)

        val failure = Result.Error(Exception("Test exception"))
        viewModel.goalsLiveData.postValue(failure)
        verify(observer).onChanged(failure)
    }
}