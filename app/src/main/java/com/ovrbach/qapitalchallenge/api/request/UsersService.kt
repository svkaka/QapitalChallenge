package com.ovrbach.qapitalchallenge.api.request

import com.ovrbach.qapitalchallenge.data.entity.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface UsersService {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: String): Observable<User>

}