package com.andrewvychev.railwaytickets.ui.findroute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpFragment
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import com.andrewvychev.railwaytickets.ui.findroute.models.FromSearchModel
import com.andrewvychev.railwaytickets.ui.findroute.models.ToSearchModel
import com.andrewvychev.railwaytickets.ui.tickets.TicketsFragment
import com.andrewvychev.railwaytickets.util.showSnackbar
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import ir.mirrajabi.searchdialog.core.Searchable
import kotlinx.android.synthetic.main.frament_find_route.btn_next
import kotlinx.android.synthetic.main.frament_find_route.et_date
import kotlinx.android.synthetic.main.frament_find_route.et_from
import kotlinx.android.synthetic.main.frament_find_route.et_to
import kotlinx.android.synthetic.main.frament_find_route.progress
import org.threeten.bp.LocalDate
import javax.inject.Inject


class FindRouteFragment : MvpFragment<FindRouteContract.View>(), FindRouteContract.View,
        DatePickerFragment.Listener {

    @Inject lateinit var presenter: FindRouteContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frament_find_route, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_from.setOnClickListener { onFromClicked() }
        et_to.setOnClickListener { onToClicked() }
        btn_next.setOnClickListener { onNextClicked() }
        et_date.setOnClickListener { onChooseDateClicked() }
    }

    override fun injectDependencies() {
        (activity?.application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }


    override fun getPresenter(): Contract.Presenter<FindRouteContract.View> = presenter

    fun onNextClicked() {
        presenter.onNextClicked(et_from.text.toString(),
                et_to.text.toString())
    }

    fun onFromClicked() {
        presenter.onFromClicked()
    }

    fun onToClicked() {
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

    fun onChooseDateClicked() {
        val fragment = DatePickerFragment.newInstance(LocalDate.now())
        fragment.setListener(this)
        fragment.show(fragmentManager, "")
    }

    override fun showTickets(tickets: List<TicketPOJO>?) {
        if (tickets == null) {
            activity?.showSnackbar("Tickets not found")
        } else {
            val fragmentContainerId = activity?.findViewById<FrameLayout>(R.id.fragment_container)?.id ?: return
            fragmentManager?.beginTransaction()
                    ?.replace(fragmentContainerId, TicketsFragment.getInstance(ArrayList(tickets)))
                    ?.commit()
        }
    }

    override fun setProgressVisible(visible: Boolean) {
        progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDateSet(date: LocalDate) {
        et_date.setText(date.toString(), TextView.BufferType.EDITABLE)
        presenter.onDateChoosed(date)
    }

    private fun showSearchDialog(items: List<Searchable>, onComplete: (Searchable) -> Unit) {
        SimpleSearchDialogCompat(activity,
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
