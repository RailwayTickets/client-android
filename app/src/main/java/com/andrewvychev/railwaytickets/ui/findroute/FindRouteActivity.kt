package com.andrewvychev.railwaytickets.ui.findroute

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpActivity
import com.andrewvychev.railwaytickets.data.pojo.TrainPOJO
import com.andrewvychev.railwaytickets.ui.findroute.models.FromSearchModel
import com.andrewvychev.railwaytickets.ui.findroute.models.ToSearchModel
import com.andrewvychev.railwaytickets.ui.tickets.TicketsActivity
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import ir.mirrajabi.searchdialog.core.Searchable
import kotlinx.android.synthetic.main.activity_find_route.drawer_layout
import kotlinx.android.synthetic.main.activity_find_route.nav_view
import kotlinx.android.synthetic.main.app_bar_find_route.toolbar
import kotlinx.android.synthetic.main.content_find_route.et_date
import kotlinx.android.synthetic.main.content_find_route.et_from
import kotlinx.android.synthetic.main.content_find_route.et_to
import kotlinx.android.synthetic.main.content_find_route.progress
import org.threeten.bp.LocalDate
import javax.inject.Inject


class FindRouteActivity : MvpActivity<FindRouteContract.View>(), FindRouteContract.View,
        DatePickerFragment.Listener, NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: FindRouteContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_route)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun injectDependencies() {
        (application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<FindRouteContract.View> = presenter

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onNextClicked(view: View) {
        presenter.onNextClicked(et_from.text.toString(),
                et_to.text.toString())
    }

    fun onFromClicked(view: View) {
        presenter.onFromClicked()
    }

    fun onToClicked(view: View) {
        presenter.onToClicked()
    }

    override fun showSearchDialogFrom(from: List<FromSearchModel>) {
        showSearchDialog(from, {
            et_from.setText(it.title, TextView.BufferType.EDITABLE)
        })
    }

    override fun showSearchDialogTo(to: List<ToSearchModel>) {
        showSearchDialog(to, {
            et_to.setText(it.title, TextView.BufferType.EDITABLE)
        })
    }

    override fun onBackPressed() {
    }

    fun onChooseDateClicked(view: View) {
        val fragment = DatePickerFragment.newInstance(LocalDate.now())
        fragment.setListener(this)
        fragment.show(supportFragmentManager, "")
    }

    override fun showTickets(tickets: List<TrainPOJO>) {
        TicketsActivity.start(this, ArrayList(tickets))
    }

    override fun setProgressVisible(visible: Boolean) {
        progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDateSet(date: LocalDate) {
        et_date.setText(date.toString(), TextView.BufferType.EDITABLE)
        presenter.onDateChoosed(date)
    }

    private fun showSearchDialog(items: List<Searchable>, onComplete: (Searchable) -> Unit) {
        SimpleSearchDialogCompat(this,
                "Search...",
                "What are you looking for...?",
                null,
                ArrayList(items),
                SearchResultListener<Searchable> { dialog, item, position ->
                    onComplete(item)
                    dialog.dismiss()
                }).show()
    }
}
