package com.andrewvychev.railwaytickets.util

import rx.Completable
import rx.Observable
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Transforms the source observable to subscribe in
 * the io thread and observe on the Android main thread.
 */
fun <T> Observable<T>.applyIoToMainThread(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyIoToMainThread(): Completable {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyIoToMainThread(): Single<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}