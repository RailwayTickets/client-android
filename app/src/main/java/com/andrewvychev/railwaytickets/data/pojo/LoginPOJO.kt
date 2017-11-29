package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 11/29/17.
 */
data class LoginPOJO(@SerializedName("login") @Expose var login: String,
                     @SerializedName("password") @Expose var password: String)