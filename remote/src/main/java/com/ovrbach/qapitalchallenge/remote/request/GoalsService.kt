package com.ovrbach.qapitalchallenge.remote.request

import com.ovrbach.qapitalchallenge.remote.wrapper.SavingGoalsWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface GoalsService {

    @GET("savingsgoals")
    fun getSavedGoals(): Single<SavingGoalsWrapper>

}