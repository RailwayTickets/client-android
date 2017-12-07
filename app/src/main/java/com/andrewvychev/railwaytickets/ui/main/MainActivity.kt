package com.andrewvychev.railwaytickets.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.ui.findroute.FindRouteFragment
import com.andrewvychev.railwaytickets.ui.mytickets.MyTicketsFragment
import com.andrewvychev.railwaytickets.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.app_bar_main.toolbar

/**
 * Created by Andrew on 12/6/17.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val fragment = FindRouteFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
    }

    override fun onBackPressed() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.nav_profile -> {
                val profile = ProfileFragment()
                transaction.replace(R.id.fragment_container, profile)
            }
            R.id.nav_buy_ticket -> {
                val findRoute = FindRouteFragment()
                transaction.replace(R.id.fragment_container, findRoute)
            }
            R.id.nav_tickets -> {
                val myTickets = MyTicketsFragment()
                transaction.replace(R.id.fragment_container, myTickets)
            }
        }
        transaction.commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}