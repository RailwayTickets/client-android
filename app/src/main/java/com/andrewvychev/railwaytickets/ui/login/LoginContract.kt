package com.andrewvychev.railwaytickets.ui.login

import com.andrewvychev.railwaytickets.base.Contract

/**
 * Created by Andrew on 11/29/17.
 */
interface LoginContract {

    interface View : Contract.View {

        fun showError(text: String)

        fun showFindRoute()

        fun showRegister()

        fun setProgressVisible(visible: Boolean)

    }

    interface Presenter : Contract.Presenter<View> {

        fun onLoginClicked(login: String, password: String)

        fun onRegisterClicked()

    }
}