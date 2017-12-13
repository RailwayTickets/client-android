package com.andrewvychev.railwaytickets.ui.returnTicket

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import kotlinx.android.synthetic.main.fragment_return.btn_return
import kotlinx.android.synthetic.main.fragment_return.tickets
import rx.lang.kotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Andrew on 12/13/17.
 */
class ReturnFragment : Fragment(), ReturnAdapter.OnReturnListener {

    lateinit var adapter: ReturnAdapter

    @Inject lateinit var routeService: RouteService

    private var checkedTickets = mutableListOf<TicketPOJO>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        injectDependencies()
        return inflater.inflate(R.layout.fragment_return, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tickets.layoutManager = LinearLayoutManager(activity)
        adapter = ReturnAdapter(activity!!, this)
        tickets.adapter = adapter

        btn_return.setOnClickListener {
            checkedTickets.forEach {
                returnTicket(it)
            }
        }

        getTickets()
    }

    fun injectDependencies() {
        (activity?.application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun onTicketChecked(ticket: TicketPOJO) {
        checkedTickets.add(ticket)
    }

    override fun onTicketUnChecked(ticket: TicketPOJO) {
        checkedTickets.remove(ticket)
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun getTickets() {
        routeService.myValidReturnTickets()
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            adapter.addAll(it.tickets)
                        },
                        onError = {
                            showToast("Error")
                        }
                )
    }

    private fun returnTicket(ticket: TicketPOJO) {
        routeService.returnTicket(ticket.id)
                .applyIoToMainThread()
                .subscribeBy(
                        onCompleted = {
                            checkedTickets.clear()
                            adapter.removeAll()
                            showToast("Success")
                        },
                        onError = {
                            showToast("Error")
                        }
                )
    }
}