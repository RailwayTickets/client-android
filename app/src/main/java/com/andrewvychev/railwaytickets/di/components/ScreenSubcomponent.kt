package com.andrewvychev.railwaytickets.di.components

import com.andrewvychev.railwaytickets.di.modules.AuthModule
import com.andrewvychev.railwaytickets.di.modules.FindRouteModule
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.findroute.FindRouteFragment
import com.andrewvychev.railwaytickets.ui.login.LoginActivity
import com.andrewvychev.railwaytickets.ui.register.RegisterActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AuthModule::class, FindRouteModule::class))
@ScreenScope
interface ScreenSubcomponent {

    fun inject(activity: LoginActivity)

    fun inject(activity: RegisterActivity)

    fun inject(activity: FindRouteFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ScreenSubcomponent

    }
}