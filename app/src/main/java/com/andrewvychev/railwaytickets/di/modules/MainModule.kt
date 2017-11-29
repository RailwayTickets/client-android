package com.andrewvychev.railwaytickets.di.modules

import android.content.Context
import com.andrewvychev.railwaytickets.RailwayApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val application: RailwayApplication) {

    @Provides
    @Singleton
    fun provideApplication(): RailwayApplication {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}
