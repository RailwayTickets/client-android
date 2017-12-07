package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.data.api.RouteService
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.mytickets.MyTicketsContract
import com.andrewvychev.railwaytickets.ui.mytickets.MyTicketsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 12/7/17.
 */
@Module
class MyTicketsModule {

    @Provides
    @ScreenScope
    internal fun provideMyTicketsPresenter(routeService: RouteService): MyTicketsContract.Presenter {
        return MyTicketsPresenter(routeService)
    }
}