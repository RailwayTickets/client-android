package com.andrewvychev.railwaytickets.ui.profile

import com.andrewvychev.railwaytickets.base.Contract
import com.andrewvychev.railwaytickets.data.pojo.ProfilePOJO

/**
 * Created by Andrew on 12/6/17.
 */
interface ProfileContract {


    interface View : Contract.View {

        fun setProfile(profile: ProfilePOJO)

    }

    interface Presenter : Contract.Presenter<View> {

        fun onSaveClicked(firstName: String,
                          lastName: String,
                          phone: String,
                          email: String,
                          doc_type: String,
                          doc_number: String)

    }
}