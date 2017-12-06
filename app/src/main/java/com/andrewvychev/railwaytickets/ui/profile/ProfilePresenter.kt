package com.andrewvychev.railwaytickets.ui.profile

import com.andrewvychev.railwaytickets.base.MvpPresenter
import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.data.pojo.ProfilePOJO
import com.andrewvychev.railwaytickets.util.applyIoToMainThread
import rx.lang.kotlin.subscribeBy

/**
 * Created by Andrew on 12/6/17.
 */
class ProfilePresenter(private val authService: AuthService)
    : MvpPresenter<ProfileContract.View>(), ProfileContract.Presenter {

    override fun attachView(view: ProfileContract.View) {
        super.attachView(view)
        authService.getProfile()
                .applyIoToMainThread()
                .subscribeBy(
                        onNext = {
                            getView()?.setProfile(it)
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }

    override fun onSaveClicked(firstName: String, lastName: String, phone: String, email: String, doc_type: String, doc_number: String) {
        authService.saveProfile(
                ProfilePOJO(
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        email = email,
                        docType = doc_type,
                        docNumber = doc_number
                )
        ).applyIoToMainThread()
                .subscribeBy(
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}