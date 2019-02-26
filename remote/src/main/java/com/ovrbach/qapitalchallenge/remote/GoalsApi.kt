package com.ovrbach.qapitalchallenge.remote

import com.ovrbach.qapitalchallenge.remote.request.FeedService
import com.ovrbach.qapitalchallenge.remote.request.GoalsService
import com.ovrbach.qapitalchallenge.remote.request.UsersService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class GoalsApi private constructor() {


    companion object {
        @Volatile
        private var INSTANCE: GoalsApi? = null

        @Volatile
        private lateinit var retrofit: Retrofit

        fun getInstance(config: ApiConfig? = null): GoalsApi {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                retrofit = buildRetrofit(config)
                val instance = GoalsApi()
                INSTANCE = instance
                instance
            }
        }

        private fun buildRetrofit(config: ApiConfig?): Retrofit {
            val builder = Retrofit.Builder()
                .apply {
                    baseUrl(BASE_URL)
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    addConverterFactory(MoshiConverterFactory.create())
                    client(buildOkHttpClient(config))
                }

            return builder.build()
        }

        private fun buildOkHttpClient(config: ApiConfig?): OkHttpClient {
            val builder = OkHttpClient.Builder().apply {
                config?.customInterceptor?.let {
                    addInterceptor(it)
                }
            }
            return builder.build()
        }
    }

    val goalService by lazy { retrofit.create(GoalsService::class.java) }
    val usersService by lazy { retrofit.create(UsersService::class.java) }
    val feedService by lazy { retrofit.create(FeedService::class.java) }
}