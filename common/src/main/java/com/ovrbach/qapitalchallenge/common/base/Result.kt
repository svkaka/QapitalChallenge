package com.ovrbach.qapitalchallenge.common.base

sealed class RequestWithParams<T> {
    data class Success<T>(val data: T, val params: Params<T>? = null) : RequestWithParams<T>()
    data class Error<T>(val error: Throwable, val params: Params<T>? = null) :
        RequestWithParams<T>()

    data class Waiting<T>(val params: Params<T>? = null) : RequestWithParams<T>()
}

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
    object Waiting : Result<Nothing>()

}

fun Result<*>?.isSuccess() = this != null && this is Result.Success

