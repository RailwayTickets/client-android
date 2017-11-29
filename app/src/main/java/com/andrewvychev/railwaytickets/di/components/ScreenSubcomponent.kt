package com.andrewvychev.railwaytickets.di.components

import com.andrewvychev.railwaytickets.di.modules.LoginModule
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoginModule::class))
@ScreenScope
interface ScreenSubcomponent {

    fun inject(activity: LoginActivity)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ScreenSubcomponent

    }
}