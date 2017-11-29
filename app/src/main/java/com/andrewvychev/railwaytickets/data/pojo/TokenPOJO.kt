package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 11/29/17.
 */
data class TokenPOJO(@SerializedName("token") @Expose var token: String,
                     @SerializedName("expires") @Expose var expires: String)