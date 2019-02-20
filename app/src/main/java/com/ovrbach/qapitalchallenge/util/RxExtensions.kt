package com.ovrbach.qapitalchallenge.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Set given [Observable] to subscribe on a new thread and observe of main thread.
 */
fun <T> Observable<T>.subscribeOnNewObserveOnMain(): Observable<T> = this
    .subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())