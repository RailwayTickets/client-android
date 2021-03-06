package com.andrewvychev.railwaytickets.ui.login

import com.andrewvychev.railwaytickets.RailwayPreferences
import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.data.pojo.LoginPOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 11/29/17.
 */
class LoginPresenter(private val authService: AuthService,
                     private val preferences: RailwayPreferences)
    : MvpPresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun onLoginClicked(login: String, password: String) {
        getView()?.setProgressVisible(true)
        authService.login(LoginPOJO(login, password))
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
                        })

    }

    override fun onRegisterClicked() {
        getView()?.showRegister()
    }
}