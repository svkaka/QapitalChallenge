package com.ovrbach.qapitalchallenge.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ovrbach.qapitalchallenge.api.GoalsApi
import com.ovrbach.qapitalchallenge.data.base.Request
import com.ovrbach.qapitalchallenge.data.entity.Goal
import com.ovrbach.qapitalchallenge.data.entity.User
import com.ovrbach.qapitalchallenge.util.subscribeOnNewObserveOnMain
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val api: GoalsApi = GoalsApi.instance

    val goalsLiveData: MutableLiveData<Request<List<Goal>>> = MutableLiveData()

    val usersLiveData: MutableLiveData<Request<User>> = MutableLiveData()

    fun fetchGoals() {
        if (usersLiveData.value == null) {
            goalsLiveData.postValue(Request.Waiting)

            val goalsDisposable = api.goalService.getSavedGoals()
                .subscribeOnNewObserveOnMain()
                .doOnNext {
                }
                .doOnError {

                }
                .subscribe(
                    { goalsWrapper ->
                        val goals = goalsWrapper.savingsGoals
                        goalsLiveData.postValue(Request.Success(goals))
                    },
                    { goalsLiveData.postValue(Request.Error(it)) }
                )

            compositeDisposable.add(goalsDisposable)
        } else {
            usersLiveData.postValue(usersLiveData.value)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}