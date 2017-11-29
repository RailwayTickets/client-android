package com.andrewvychev.railwaytickets.ui.register

import com.andrewvychev.railwaytickets.base.Contract

/**
 * Created by Andrew on 11/29/17.
 */
interface RegisterContract {

    interface View: Contract.View {

        fun setProgressVisible(visible: Boolean)

        fun showError(text: String)

        fun showFindRoute()

    }

    interface Presenter: Contract.Presenter<View> {

        fun onRegisterClicked(login: String, password: String, confirmPassword: String)

    }

}