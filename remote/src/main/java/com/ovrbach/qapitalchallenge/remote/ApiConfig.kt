package com.ovrbach.qapitalchallenge.remote

import okhttp3.Interceptor

class ApiConfig private constructor(val customInterceptor: Interceptor? = null) {

    public class Builder() {
        var customInterceptor: Interceptor? = null

        fun build() = ApiConfig(customInterceptor)
    }
}