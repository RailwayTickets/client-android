package com.andrewvychev.railwaytickets.ui.tickets.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter
import com.afollestad.sectionedrecyclerview.SectionedViewHolder
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import kotlinx.android.synthetic.main.item_ticket.view.tv_carriage
import kotlinx.android.synthetic.main.item_ticket.view.tv_departure
import kotlinx.android.synthetic.main.item_ticket.view.tv_from
import kotlinx.android.synthetic.main.item_ticket.view.tv_seat
import kotlinx.android.synthetic.main.item_ticket.view.tv_to
import kotlinx.android.synthetic.main.item_ticket.view.tv_type
import kotlinx.android.synthetic.main.section_header.view.iv_arrow
import kotlinx.android.synthetic.main.section_header.view.tv_header

/**
 * Created by Andrew on 12/6/17.
 */
class TicketsAdapter(private val context: Context,
                     private val listener: OnTicketsListener? = null)
    : SectionedRecyclerViewAdapter<TicketsAdapter.TicketsViewHolder>() {

    var items = mutableListOf<TicketPOJO>()

    fun addAll(items: Collection<TicketPOJO>) {
        if (!items.isEmpty()) {
            val start = this.items.size
            this.items.addAll(items)
            notifyItemRangeInserted(start, items.size)
        }
    }

    override fun onBindViewHolder(holder: TicketsViewHolder?, section: Int, relativePosition: Int, absolutePosition: Int) {
        if (section == 0) {
            holder?.bind(getReserved()[relativePosition])
        } else {
            holder?.bind(getCompartment()[relativePosition])
        }
    }

    override fun onBindFooterViewHolder(holder: TicketsViewHolder?, section: Int) {
    }

    override fun getSectionCount(): Int {
        return 2
    }

    override fun onBindHeaderViewHolder(holder: TicketsViewHolder?, section: Int, expanded: Boolean) {
        holder?.bind(if (section == 0) "reserved" else "compartment")
    }

    override fun getItemCount(section: Int): Int {
        return when (section) {
            0 -> getReserved().size
            1 -> getCompartment().size
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TicketsViewHolder {
        var layoutRes = 0
        when (viewType) {
            VIEW_TYPE_HEADER -> {
                layoutRes = R.layout.section_header
            }
            VIEW_TYPE_ITEM -> {
                layoutRes = R.layout.item_ticket
            }
        }
        val view = LayoutInflater.from(context).inflate(layoutRes, parent, false)
        return TicketsViewHolder(view, this)
    }

    fun getReserved() = items.filter { it.type == "reserved" }

    fun getCompartment() = items.filter { it.type == "compartment" }

    inner class TicketsViewHolder(private val root: View,
                                  private val adapter: TicketsAdapter) : SectionedViewHolder(root) {

        fun bind(title: String) {
            root.tv_header.text = title
            root.setOnClickListener {
                val section = relativePosition.section()
                if (adapter.isSectionExpanded(section)) {
                    adapter.collapseSection(section)
                    root.iv_arrow.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.ic_keyboard_arrow_up_black_24dp))
                } else {
                    adapter.expandSection(section)
                    root.iv_arrow.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.ic_keyboard_arrow_down_black_24dp))
                }
            }
        }

        fun bind(ticket: TicketPOJO) {
            root.setOnClickListener {
                listener?.onTicketClicked(ticket)
            }

            root.tv_from.text = String.format(context.getString(R.string.tickets_from), ticket.from)
            root.tv_to.text = String.format(context.getString(R.string.tickets_to), ticket.to)
            root.tv_carriage.text = String.format(context.getString(R.string.tickets_carriage), ticket.carriage)
            root.tv_departure.text = String.format(context.getString(R.string.tickets_departure), ticket.departure)
            root.tv_seat.text = String.format(context.getString(R.string.tickets_seat), ticket.seat)
            root.tv_type.text = String.format(context.getString(R.string.tickets_type), ticket.type)
        }
    }

    interface OnTicketsListener {

        fun onTicketClicked(ticket: TicketPOJO)

    }
}