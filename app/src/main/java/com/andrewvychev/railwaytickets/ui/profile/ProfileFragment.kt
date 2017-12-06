package com.andrewvychev.railwaytickets.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrewvychev.railwaytickets.R
import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.base.MvpFragment
import com.andrewvychev.railwaytickets.data.pojo.ProfilePOJO
import kotlinx.android.synthetic.main.fragment_profile.btn_save
import kotlinx.android.synthetic.main.fragment_profile.et_doc_number
import kotlinx.android.synthetic.main.fragment_profile.et_doc_type
import kotlinx.android.synthetic.main.fragment_profile.et_email
import kotlinx.android.synthetic.main.fragment_profile.et_first_name
import kotlinx.android.synthetic.main.fragment_profile.et_last_name
import kotlinx.android.synthetic.main.fragment_profile.et_phone
import javax.inject.Inject

/**
 * Created by Andrew on 12/6/17.
 */
class ProfileFragment: MvpFragment<ProfileContract.View>(), ProfileContract.View {

    @Inject lateinit var presenter: ProfileContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save.setOnClickListener {
            presenter.onSaveClicked(
                    firstName = et_first_name.text.toString(),
                    lastName = et_last_name.text.toString(),
                    phone = et_phone.text.toString(),
                    email = et_email.text.toString(),
                    doc_type = et_doc_type.text.toString(),
                    doc_number = et_doc_number.text.toString()
            )
        }
    }

    override fun setProfile(profile: ProfilePOJO) {
        et_first_name.setText(profile.firstName)
        et_last_name.setText(profile.lastName)
        et_doc_number.setText(profile.docNumber)
        et_doc_type.setText(profile.docType)
        et_email.setText(profile.email)
        et_phone.setText(profile.phone)
    }

    override fun injectDependencies() {
        (activity?.application as? RailwayApplication)?.appComponent
                ?.activityComponent()
                ?.build()
                ?.inject(this)
    }

    override fun getPresenter(): Contract.Presenter<ProfileContract.View> = presenter

}