package com.andrewvychev.railwaytickets.di.components

import com.andrewvychev.railwaytickets.di.modules.AuthModule
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.login.LoginActivity
import com.andrewvychev.railwaytickets.ui.register.RegisterActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AuthModule::class))
@ScreenScope
interface ScreenSubcomponent {

    fun inject(activity: LoginActivity)

    fun inject(activity: RegisterActivity)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ScreenSubcomponent

    }
}