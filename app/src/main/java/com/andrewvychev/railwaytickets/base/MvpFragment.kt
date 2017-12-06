package com.andrewvychev.railwaytickets.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Andrew on 7/6/17.
 */

abstract class MvpFragment<V : Contract.View> : Fragment(),
        Contract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectDependencies()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        getPresenter().attachView(this as V)
    }

    protected abstract fun injectDependencies()

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView()
    }

    protected abstract fun getPresenter(): Contract.Presenter<V>
}
