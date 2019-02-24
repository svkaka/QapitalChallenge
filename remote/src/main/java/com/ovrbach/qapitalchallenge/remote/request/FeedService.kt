package com.ovrbach.qapitalchallenge.remote.request

import com.ovrbach.qapitalchallenge.common.base.GoalId
import com.ovrbach.qapitalchallenge.remote.wrapper.FeedWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedService {

    @GET("savingsgoals/{id}/feed")
    fun getFeed(@Path("id") goalId: GoalId): Single<FeedWrapper>

}