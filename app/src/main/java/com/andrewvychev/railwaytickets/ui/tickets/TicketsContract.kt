package com.andrewvychev.railwaytickets.ui.tickets

import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO

/**
 * Created by Andrew on 12/6/17.
 */
interface TicketsContract {

    interface View : Contract.View {

        fun showMessage(message: String)

    }

    interface Presenter : Contract.Presenter<View> {

        fun onTicketClicked(ticket: TicketPOJO)

    }
}