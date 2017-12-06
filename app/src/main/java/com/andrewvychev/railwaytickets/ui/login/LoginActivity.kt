package com.andrewvychev.railwaytickets.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpActivity
import com.andrewvychev.railwaytickets.ui.findroute.FindRouteFragment
import com.andrewvychev.railwaytickets.ui.main.MainActivity
import com.andrewvychev.railwaytickets.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.et_login
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.progress
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
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showFindRoute() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun setProgressVisible(visible: Boolean) {
        progress.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

