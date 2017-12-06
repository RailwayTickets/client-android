package com.andrewvychev.railwaytickets.ui.tickets.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewvychev.railwaytickets.ArrayRecyclerAdapter
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import kotlinx.android.synthetic.main.item_ticket.view.tv_carriage
import kotlinx.android.synthetic.main.item_ticket.view.tv_departure
import kotlinx.android.synthetic.main.item_ticket.view.tv_from
import kotlinx.android.synthetic.main.item_ticket.view.tv_seat
import kotlinx.android.synthetic.main.item_ticket.view.tv_to
import kotlinx.android.synthetic.main.item_ticket.view.tv_type

/**
 * Created by Andrew on 12/6/17.
 */
class TicketsAdapter(private val context: Context,
                     private val listener: OnTicketsListener)
    : ArrayRecyclerAdapter<TicketPOJO, TicketsAdapter.TicketsViewHolder>() {

    override fun onBindViewHolder(holder: TicketsViewHolder?, position: Int) {
        holder?.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TicketsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false)
        return TicketsViewHolder(view)
    }

    inner class TicketsViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(ticket: TicketPOJO) {
            root.tv_from.text = String.format(context.getString(R.string.tickets_from), ticket.from)
            root.tv_to.text = String.format(context.getString(R.string.tickets_to), ticket.to)
            root.tv_carriage.text = String.format(context.getString(R.string.tickets_carriage), ticket.carriage)
            root.tv_departure.text = String.format(context.getString(R.string.tickets_departure), ticket.departure)
            root.tv_seat.text = String.format(context.getString(R.string.tickets_seat), ticket.seat)
            root.tv_type.text = String.format(context.getString(R.string.tickets_type), ticket.type)
            root.setOnClickListener {
                listener.onTicketClicked(getItem(adapterPosition))
            }
        }
    }

    interface OnTicketsListener {

        fun onTicketClicked(ticket: TicketPOJO)

    }
}