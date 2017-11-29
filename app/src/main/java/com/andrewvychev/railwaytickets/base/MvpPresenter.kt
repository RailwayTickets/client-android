package com.andrewvychev.railwaytickets.base

import rx.subscriptions.CompositeSubscription

/**
 * Created by Andrew on 7/6/17.
 */
abstract class MvpPresenter<V : Contract.View> : Contract.Presenter<V> {

    internal val subscriptions = CompositeSubscription()

    private var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        subscriptions.clear()
        view = null
    }

    override fun getView(): V? {
        return view
    }
}