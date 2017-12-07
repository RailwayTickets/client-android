package com.andrewvychev.railwaytickets.ui.mytickets

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpFragment
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.ui.tickets.adapter.TicketsAdapter
import kotlinx.android.synthetic.main.fragment_my_tickets.rv_tickets
import javax.inject.Inject

/**
 * Created by Andrew on 12/7/17.
 */
class MyTicketsFragment: MvpFragment<MyTicketsContract.View>(), MyTicketsContract.View {

    @Inject lateinit var presenter: MyTicketsContract.Presenter

    private lateinit var adapter: TicketsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_my_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tickets.layoutManager = LinearLayoutManager(activity)
        adapter = TicketsAdapter(activity!!)
        rv_tickets.adapter = adapter
    }

    override fun addItems(items: List<TicketPOJO>) {
        adapter.addAll(items)
    }

    override fun injectDependencies() {
        (activity?.application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<MyTicketsContract.View> = presenter
}