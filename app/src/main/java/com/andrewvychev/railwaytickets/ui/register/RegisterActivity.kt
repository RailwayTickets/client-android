package com.andrewvychev.railwaytickets.ui.register

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
import kotlinx.android.synthetic.main.activity_register.et_confirm_password
import kotlinx.android.synthetic.main.activity_register.et_login
import kotlinx.android.synthetic.main.activity_register.et_password
import kotlinx.android.synthetic.main.activity_register.progress
import javax.inject.Inject

class RegisterActivity : MvpActivity<RegisterContract.View>(), RegisterContract.View {

    @Inject lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun setProgressVisible(visible: Boolean) {
        progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun onRegisterClicked(view: View) {
        presenter.onRegisterClicked(
                login = et_login.text.toString(),
                password = et_password.text.toString(),
                confirmPassword = et_confirm_password.text.toString()
        )
    }

    override fun injectDependencies() {
        (application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<RegisterContract.View> = presenter

    override fun showFindRoute() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
