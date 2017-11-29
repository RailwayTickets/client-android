package com.andrewvychev.railwaytickets.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Andrew on 7/6/17.
 */

abstract class MvpActivity<V : Contract.View> : AppCompatActivity(),
        Contract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
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
