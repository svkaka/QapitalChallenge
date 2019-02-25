package com.ovrbach.qapitalchallenge.common.base

class Response<T>(val source: Source, val data: T?)

enum class Source {
    LOCAL,
    REMOTE;

    fun isRemote() = this == REMOTE
}