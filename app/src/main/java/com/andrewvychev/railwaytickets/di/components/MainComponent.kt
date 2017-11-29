package com.andrewvychev.railwaytickets.di.components

import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.di.modules.MainModule
import com.andrewvychev.railwaytickets.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(MainModule::class, NetworkModule::class))
@Singleton
interface MainComponent {

    fun inject(application: RailwayApplication)

    fun activityComponent(): ScreenSubcomponent.Builder
}

