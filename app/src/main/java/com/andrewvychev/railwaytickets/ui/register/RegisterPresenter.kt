package com.andrewvychev.railwaytickets.ui.register

import com.andrewvychev.railwaytickets.RailwayPreferences
import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.data.pojo.RegisterPOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 11/29/17.
 */
class RegisterPresenter(private val authService: AuthService,
                        private val preferences: RailwayPreferences)
    : MvpPresenter<RegisterContract.View>(), RegisterContract.Presenter {


    override fun onRegisterClicked(login: String, password: String, confirmPassword: String) {
        getView()?.setProgressVisible(true)
        authService.register(RegisterPOJO(login, password, confirmPassword))
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            getView()?.setProgressVisible(false)
                            preferences.setToken(it.token)
                            getView()?.showFindRoute()
                        },
                        onError = {
                            getView()?.setProgressVisible(false)
                            getView()?.showError("ERROR")
                        }
                )

    }
}