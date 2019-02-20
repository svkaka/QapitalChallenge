package com.ovrbach.qapitalchallenge.api

import com.ovrbach.qapitalchallenge.api.request.FeedService
import com.ovrbach.qapitalchallenge.api.request.GoalsService
import com.ovrbach.qapitalchallenge.api.request.UsersService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class GoalsApi private constructor() {

    companion object {
        val instance = GoalsApi()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val goalService: GoalsService by lazy { retrofit.create(GoalsService::class.java) }
    val usersService by lazy { retrofit.create(UsersService::class.java) }
    val feedService by lazy { retrofit.create(FeedService::class.java) }
}