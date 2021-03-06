package com.andrewvychev.railwaytickets.ui.findroute

import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.ui.findroute.models.FromSearchModel
import com.andrewvychev.railwaytickets.ui.findroute.models.ToSearchModel
import org.threeten.bp.LocalDate

/**
 * Created by Andrew on 11/29/17.
 */
interface FindRouteContract {

    interface View : Contract.ProgressView {

        fun showTickets(tickets: List<TicketPOJO>?)

        fun showSearchDialogFrom(from: List<FromSearchModel>)

        fun showSearchDialogTo(to: List<ToSearchModel>)

    }

    interface Presenter : Contract.Presenter<View> {

        fun onDateChoosed(date: LocalDate)

        fun onNextClicked(from: String, to: String)

        fun onFromClicked()

        fun onToClicked()
    }
}