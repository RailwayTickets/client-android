package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.findroute.FindRouteContract
import com.andrewvychev.railwaytickets.ui.findroute.FindRoutePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 11/29/17.
 */
@Module
class FindRouteModule {

    @Provides
    @ScreenScope
    internal fun provideFindRoutePresenter(routeService: RouteService): FindRouteContract.Presenter {
        return FindRoutePresenter(routeService)
    }
}