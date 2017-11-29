package com.andrewvychev.railwaytickets.ui.tickets

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.data.pojo.TrainPOJO
import kotlinx.android.synthetic.main.activity_tickets.tv_tickets

class TicketsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_TICKETS = "tickets"

        fun start(context: Context, tickets: ArrayList<TrainPOJO>) {
            val intent = Intent(context, TicketsActivity::class.java)
                    .putExtra(EXTRA_TICKETS, tickets)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        val tickets = intent.getSerializableExtra(EXTRA_TICKETS) as ArrayList<TrainPOJO>
        tv_tickets.text = tickets.toString()
    }
}
