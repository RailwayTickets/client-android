package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.di.scopes.ScreenScope
import com.andrewvychev.railwaytickets.ui.profile.ProfileContract
import com.andrewvychev.railwaytickets.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Andrew on 12/6/17.
 */
@Module
class ProfileModule {

    @Provides
    @ScreenScope
    internal fun provideProfilePresenter(authService: AuthService): ProfileContract.Presenter {
        return ProfilePresenter(authService)
    }
}