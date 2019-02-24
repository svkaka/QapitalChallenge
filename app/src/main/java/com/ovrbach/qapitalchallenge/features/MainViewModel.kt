package com.ovrbach.qapitalchallenge.features

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ovrbach.qapitalchallenge.Repository
import com.ovrbach.qapitalchallenge.common.base.Result
import com.ovrbach.qapitalchallenge.common.base.isSuccess
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.util.subscribeOnNewObserveOnMain
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(context: Application) : AndroidViewModel(context) {

    private val compositeDisposable = CompositeDisposable()

    private val repository = Repository.getInstance(getApplication())

    val goalsLiveData: MutableLiveData<Result<List<Goal>>> = MutableLiveData()

    fun fetchGoals() {
        goalsLiveData.postValue(Result.Waiting)

        val goalsDisposable = repository.getGoals()
            .subscribeOnNewObserveOnMain()
            .subscribe(
                { response ->
                    val goals = response.data
                    goalsLiveData.postValue(Result.Success(goals))
                    if (response.source.isRemote()) {
                        repository.updateGoalsDatabase(goals)
                    }
                },
                {
                    if (!goalsLiveData.value.isSuccess()) {
                        goalsLiveData.postValue(Result.Error(it))
                    }
                }
            )

        compositeDisposable.add(goalsDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}