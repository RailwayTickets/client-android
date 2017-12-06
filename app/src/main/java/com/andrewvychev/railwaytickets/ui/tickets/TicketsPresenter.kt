package com.andrewvychev.railwaytickets.ui.tickets

import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 12/6/17.
 */
class TicketsPresenter(private val routeService: RouteService) : MvpPresenter<TicketsContract.View>(), TicketsContract.Presenter {

    override fun onTicketClicked(ticket: TicketPOJO) {
        routeService.buy(ticket.id)
                .applyIoToMainThread()
                .subscribeBy(
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}