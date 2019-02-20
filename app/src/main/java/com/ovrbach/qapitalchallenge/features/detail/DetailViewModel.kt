package com.ovrbach.qapitalchallenge.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ovrbach.qapitalchallenge.api.GoalsApi
import com.ovrbach.qapitalchallenge.data.base.Request
import com.ovrbach.qapitalchallenge.data.entity.Feed
import com.ovrbach.qapitalchallenge.util.MILLIS_IN_WEEK
import com.ovrbach.qapitalchallenge.util.subscribeOnNewObserveOnMain
import com.ovrbach.qapitalchallenge.util.sumBy
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class DetailViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val api: GoalsApi = GoalsApi.instance

    val feedMutableData: MutableLiveData<Request<List<Feed>>> = MutableLiveData()
    val lastWeekSum: LiveData<Float> = Transformations.map(feedMutableData) { request ->
        (request as? Request.Success<List<Feed>>)?.also {
            it.data
                .filter { feed -> feed.date() > Date(System.currentTimeMillis() - MILLIS_IN_WEEK) }
                .sumBy { feed -> feed.amount }
        }
        0f
    }

    fun loadFeed(id: Int) {
        if (feedMutableData.value == null) {
            feedMutableData.postValue(Request.Waiting)

            val disposable = api.feedService.getFeed(id)
                .subscribeOnNewObserveOnMain()
                .subscribe(
                    { wrapper ->
                        val feed = wrapper.feed
                        feedMutableData.postValue(Request.Success(feed))
                    },
                    { feedMutableData.postValue(Request.Error(it)) }
                )

            compositeDisposable.add(disposable)
        } else {
            feedMutableData.postValue(Request.Waiting)
        }
    }
}