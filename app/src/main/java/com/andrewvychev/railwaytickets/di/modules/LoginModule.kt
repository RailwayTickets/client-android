package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.login.LoginContract
import com.andrewvychev.railwaytickets.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 11/29/17.
 */
@Module
class LoginModule {

    @Provides
    @ScreenScope
    internal fun provideLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }
}