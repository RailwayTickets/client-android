package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.tickets.TicketsContract
import com.andrewvychev.railwaytickets.ui.tickets.TicketsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 12/6/17.
 */
@Module
class TicketsModule {

    @Provides
    @ScreenScope
    internal fun provideTicketsPresenter(routeService: RouteService): TicketsContract.Presenter {
        return TicketsPresenter(routeService)
    }
}