package com.ovrbach.qapitalchallenge.api.request

import com.ovrbach.qapitalchallenge.data.entity.Goal
import com.ovrbach.qapitalchallenge.data.wrapper.SavingGoalsWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface GoalsService {

    @GET("savingsgoals")
    fun getSavedGoals(): Observable<SavingGoalsWrapper>

}