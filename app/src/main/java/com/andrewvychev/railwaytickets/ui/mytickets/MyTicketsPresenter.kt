package com.andrewvychev.railwaytickets.ui.mytickets

import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 12/7/17.
 */
class MyTicketsPresenter(private val findRouteService: RouteService)
    : MvpPresenter<MyTicketsContract.View>(), MyTicketsContract.Presenter {

    override fun attachView(view: MyTicketsContract.View) {
        super.attachView(view)
        findRouteService.myTickets()
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            getView()?.addItems(it.tickets)
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}