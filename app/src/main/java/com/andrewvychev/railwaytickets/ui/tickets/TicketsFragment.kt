package com.andrewvychev.railwaytickets.ui.tickets

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpFragment
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.ui.tickets.adapter.TicketsAdapter
import kotlinx.android.synthetic.main.fragment_tickets.rv_tickets
import javax.inject.Inject

class TicketsFragment : MvpFragment<TicketsContract.View>(), TicketsContract.View,
        TicketsAdapter.OnTicketsListener {

    @Inject lateinit var presenter: TicketsContract.Presenter

    lateinit var adapter: TicketsAdapter

    companion object {

        private const val ARG_TICKETS = "tickets"

        fun getInstance(tickets: ArrayList<TicketPOJO>): TicketsFragment {
            val fragment = TicketsFragment()
            val bundle = Bundle(1)
            bundle.putSerializable(ARG_TICKETS, tickets)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tickets = arguments?.getSerializable(ARG_TICKETS) as ArrayList<TicketPOJO>
        adapter = TicketsAdapter(activity!!, this)
        adapter.addAll(tickets)

        rv_tickets.layoutManager = LinearLayoutManager(activity)
        rv_tickets.adapter = adapter
    }

    override fun injectDependencies() {
        (activity?.application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<TicketsContract.View> = presenter

    override fun onTicketClicked(ticket: TicketPOJO) {
        presenter.onTicketClicked(ticket)
    }
}
