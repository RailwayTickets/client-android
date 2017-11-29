package com.andrewvychev.railwaytickets.ui.findroute

import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.data.pojo.TrainPOJO
import org.threeten.bp.LocalDate

/**
 * Created by Andrew on 11/29/17.
 */
interface FindRouteContract {

    interface View: Contract.View {

        fun showTickets(tickets: List<TrainPOJO>)

    }

    interface Presenter: Contract.Presenter<View> {

        fun onDateChoosed(date: LocalDate)

        fun onNextClicked(from: String, to: String)

    }
}