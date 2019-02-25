package com.ovrbach.qapitalchallenge.features.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ovrbach.qapitalchallenge.Repository
import com.ovrbach.qapitalchallenge.common.base.Result
import com.ovrbach.qapitalchallenge.common.base.Source
import com.ovrbach.qapitalchallenge.common.base.isSuccess
import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.setGoalId
import com.ovrbach.qapitalchallenge.common.logic.MILLIS_IN_WEEK
import com.ovrbach.qapitalchallenge.common.logic.sumByFloat
import com.ovrbach.qapitalchallenge.util.subscribeOnNewObserveOnMain
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class DetailViewModel(context: Application) : AndroidViewModel(context) {

    private val compositeDisposable = CompositeDisposable()

    private val repository = Repository.getInstance(getApplication())

    val feedMutableData: MutableLiveData<Result<List<Feed>>> =
        MutableLiveData()
    val lastWeekSum: LiveData<Float> = Transformations.map(feedMutableData) { result ->
        (result as? Result.Success<List<Feed>>)?.let {
            it.data
                .filter { feed -> feed.date > Date(System.currentTimeMillis() - MILLIS_IN_WEEK) }
                .sumByFloat { feed -> feed.amount }
        } ?: 0F
    }

    fun loadFeed(id: Int) {
        feedMutableData.postValue(Result.Waiting)

        val disposable = repository.getFeedForGoal(id)
            .subscribeOnNewObserveOnMain()
            .subscribe(
                { response ->
                    val feed = response.data

                    if (feed != null) {
                        if (response.source == Source.REMOTE) {
                            feed.setGoalId(id)
                            repository.updateFeedDatabase(feed)
                        }
                        feedMutableData.postValue(
                            Result.Success(feed)
                        )
                    }

                },
                {
                    if (!feedMutableData.value.isSuccess()) {
                        feedMutableData.postValue(Result.Error(it))
                    }
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

