package com.andrewvychev.railwaytickets

import android.app.Application
import com.andrewvychev.railwaytickets.di.components.DaggerMainComponent
import com.andrewvychev.railwaytickets.di.components.MainComponent
import com.andrewvychev.railwaytickets.di.modules.MainModule
import com.andrewvychev.railwaytickets.di.modules.NetworkModule

/**
 * Created by Andrew on 11/29/17.
 */
class RailwayApplication: Application() {

    lateinit var appComponent: MainComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .networkModule(NetworkModule(this))
                .build()
    }
}