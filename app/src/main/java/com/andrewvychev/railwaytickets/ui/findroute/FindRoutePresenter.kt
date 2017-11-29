package com.andrewvychev.railwaytickets.ui.findroute

import android.util.Log
import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.data.pojo.SearchPOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import org.threeten.bp.LocalDate
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 11/29/17.
 */
class FindRoutePresenter(private val routeService: RouteService)
    : MvpPresenter<FindRouteContract.View>(), FindRouteContract.Presenter {

    private var date: LocalDate? = null

    override fun onDateChoosed(date: LocalDate) {
        this.date = date
    }

    override fun onNextClicked(from: String, to: String) {
        date ?: return
        routeService.seach(SearchPOJO(from, to, date?.toString()!!))
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            Log.d("YRA", it.toString())
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}