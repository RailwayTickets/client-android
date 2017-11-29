package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.RailwayPreferences
import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.login.LoginContract
import com.andrewvychev.railwaytickets.ui.login.LoginPresenter
import com.andrewvychev.railwaytickets.ui.register.RegisterContract
import com.andrewvychev.railwaytickets.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 11/29/17.
 */
@Module
class AuthModule {

    @Provides
    @ScreenScope
    internal fun provideLoginPresenter(authService: AuthService,
                                       preferences: RailwayPreferences): LoginContract.Presenter {
        return LoginPresenter(authService, preferences)
    }

    @Provides
    @ScreenScope
    internal fun provideRegisterPresenter(authService: AuthService,
                                          preferences: RailwayPreferences): RegisterContract.Presenter {
        return RegisterPresenter(authService, preferences)
    }
}