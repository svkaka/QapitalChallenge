package com.ovrbach.qapitalchallenge.common.base

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
    object Waiting : Result<Nothing>()
}

fun Result<*>?.isSuccess() = this != null && this is Result.Success

