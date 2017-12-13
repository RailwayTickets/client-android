package com.andrewvychev.railwaytickets.ui.returnTicket

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewvychev.railwaytickets.ArrayRecyclerAdapter
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import kotlinx.android.synthetic.main.item_return_ticket.view.check
import kotlinx.android.synthetic.main.item_ticket.view.tv_carriage
import kotlinx.android.synthetic.main.item_ticket.view.tv_departure
import kotlinx.android.synthetic.main.item_ticket.view.tv_from
import kotlinx.android.synthetic.main.item_ticket.view.tv_seat
import kotlinx.android.synthetic.main.item_ticket.view.tv_to
import kotlinx.android.synthetic.main.item_ticket.view.tv_type

/**
 * Created by Andrew on 12/13/17.
 */
class ReturnAdapter(private val context: Context,
                    private val listener: OnReturnListener?) : ArrayRecyclerAdapter<TicketPOJO, ReturnAdapter.ReturnViewHolder>() {

    override fun onBindViewHolder(holder: ReturnViewHolder?, position: Int) {
        holder?.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReturnViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_return_ticket, parent, false)
        return ReturnViewHolder(view)
    }

    inner class ReturnViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(ticket: TicketPOJO) {
            root.tv_from.text = String.format(context.getString(R.string.tickets_from), ticket.from)
            root.tv_to.text = String.format(context.getString(R.string.tickets_to), ticket.to)
            root.tv_carriage.text = String.format(context.getString(R.string.tickets_carriage), ticket.carriage)
            root.tv_departure.text = String.format(context.getString(R.string.tickets_departure), ticket.departure)
            root.tv_seat.text = String.format(context.getString(R.string.tickets_seat), ticket.seat)
            root.tv_type.text = String.format(context.getString(R.string.tickets_type), ticket.type)
            root.check.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    listener?.onTicketChecked(ticket)
                } else {
                    listener?.onTicketUnChecked(ticket)
                }
            }
        }
    }

    interface OnReturnListener {

        fun onTicketChecked(ticket: TicketPOJO)

        fun onTicketUnChecked(ticket: TicketPOJO)

    }
}