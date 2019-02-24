package com.ovrbach.qapitalchallenge.remote.request

import com.ovrbach.qapitalchallenge.common.entity.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface UsersService {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: String): Single<User>

}