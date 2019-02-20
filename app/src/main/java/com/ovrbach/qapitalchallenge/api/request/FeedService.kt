package com.ovrbach.qapitalchallenge.api.request

import com.ovrbach.qapitalchallenge.data.wrapper.FeedWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedService {

    @GET("savingsgoals/{id}/feed")
    fun getFeed(@Path("id") userId: Int): Observable<FeedWrapper>

}