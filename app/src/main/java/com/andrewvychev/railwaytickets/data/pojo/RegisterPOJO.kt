package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 11/29/17.
 */
data class RegisterPOJO(@SerializedName("login") @Expose var login: String,
                        @SerializedName("password") @Expose var password: String,
                        @SerializedName("confirmation") @Expose var confirmPassword: String)