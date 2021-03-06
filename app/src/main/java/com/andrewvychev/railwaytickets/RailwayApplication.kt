package com.andrewvychev.railwaytickets

import android.app.Application
import android.util.Log
import com.andrewvychev.railwaytickets.di.components.DaggerMainComponent
import com.andrewvychev.railwaytickets.di.components.MainComponent
import com.andrewvychev.railwaytickets.di.modules.MainModule
import com.andrewvychev.railwaytickets.di.modules.NetworkModule

/**
 * Created by Andrew on 11/29/17.
 */
class RailwayApplication : Application() {

    lateinit var appComponent: MainComponent
    lateinit var appPreferences: RailwayPreferences

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .networkModule(NetworkModule(this))
                .build()

        appPreferences = RailwayPreferences(this)

        Log.d("Token", appPreferences.getToken())
    }
}