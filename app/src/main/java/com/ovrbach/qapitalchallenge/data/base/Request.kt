package com.ovrbach.qapitalchallenge.data.base

sealed class RequestWithParams<T> {
    data class Success<T>(val data: T, val params: Params<T>? = null) : RequestWithParams<T>()
    data class Error<T>(val error: Throwable, val params: Params<T>? = null) : RequestWithParams<T>()
    data class Waiting<T>(val params: Params<T>? = null) : RequestWithParams<T>()
}

sealed class Request<out T : Any> {
    data class Success<out T : Any>(val data: T) : Request<T>()
    data class Error(val error: Throwable) : Request<Nothing>()
    object Waiting : Request<Nothing>()
}

//
//sealed class Request2<T> {
//    data class Success<T>(val data: T, val params: Params<T>? = null) : Request2<T>()
//    data class Error<T>(val error: Throwable) : Request2<T>()
//}


sealed class Result<T> {

}

