package com.andrewvychev.railwaytickets.ui.mytickets

import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO

/**
 * Created by Andrew on 12/7/17.
 */
interface MyTicketsContract {

    interface View: Contract.View {

        fun addItems(items: List<TicketPOJO>)

    }

    interface Presenter: Contract.Presenter<View> {

    }

}