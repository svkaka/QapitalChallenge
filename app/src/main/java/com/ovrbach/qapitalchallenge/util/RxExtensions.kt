package com.ovrbach.qapitalchallenge.util

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription


/**
 * Set given [Observable] to subscribe on a new thread and observe of main thread.
 */
fun <T> Observable<T>.subscribeOnNewObserveOnMain(): Observable<T> = this
    .subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())

/**
 * Set given [Observable] to subscribe on a new thread and observe of main thread.
 */
fun <T> Flowable<T>.subscribeOnNewObserveOnMain(): Flowable<T> = this
    .subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())


/**
 * Set given [Observable] to subscribe on a new thread and observe of main thread.
 */
fun Completable.subscribeOnNewObserveOnMain(): Completable = this
    .subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())
