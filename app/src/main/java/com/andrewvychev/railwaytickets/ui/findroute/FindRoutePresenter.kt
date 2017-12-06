package com.andrewvychev.railwaytickets.ui.findroute

import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.data.pojo.SearchPOJO
import com.andrewvychev.railwaytickets.ui.findroute.models.FromSearchModel
import com.andrewvychev.railwaytickets.ui.findroute.models.ToSearchModel
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import org.threeten.bp.LocalDate
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 11/29/17.
 */
class FindRoutePresenter(private val routeService: RouteService)
    : MvpPresenter<FindRouteContract.View>(), FindRouteContract.Presenter {

    private var date: LocalDate? = null
    private var departures: List<String>? = null
    private var directions: List<String>? = null

    override fun attachView(view: FindRouteContract.View) {
        super.attachView(view)
        getView()?.setProgressVisible(true)
        routeService.departures()
                .zipWith(routeService.directions(), { departures, directions -> departures.locations to directions.locations })
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            getView()?.setProgressVisible(false)
                            departures = it.first
                            directions = it.second
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }

    override fun onFromClicked() {
        departures?.map { FromSearchModel(it) }?.let {
            getView()?.showSearchDialogFrom(it)
        }
    }

    override fun onToClicked() {
        directions?.map { ToSearchModel(it) }?.let {
            getView()?.showSearchDialogTo(it)
        }
    }

    override fun onDateChoosed(date: LocalDate) {
        this.date = date
    }

    override fun onNextClicked(from: String, to: String) {
        date ?: return
        routeService.seach(SearchPOJO(from, to, date?.toString()!!))
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            getView()?.showTickets(it.trains)
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}