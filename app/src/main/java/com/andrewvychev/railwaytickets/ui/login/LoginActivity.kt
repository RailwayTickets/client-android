package com.andrewvychev.railwaytickets.ui.login

import android.os.Bundle
import android.view.View
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpActivity
import kotlinx.android.synthetic.main.activity_login.et_login
import kotlinx.android.synthetic.main.activity_login.et_password
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : MvpActivity<LoginContract.View>(), LoginContract.View {

    @Inject lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun injectDependencies() {
        (application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<LoginContract.View> = presenter

    fun onLoginClicked(view: View) {
        presenter.onLoginClicked(et_login.text.toString(), et_password.text.toString())
    }

    fun onRegisterClicked(view: View) {
        presenter.onRegisterClicked()
    }

    override fun showError(text: String) {

    }

    override fun showMain() {
    }

    override fun showRegister() {
    }
}

