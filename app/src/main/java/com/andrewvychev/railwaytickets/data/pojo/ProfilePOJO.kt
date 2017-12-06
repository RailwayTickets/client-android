package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 12/6/17.
 */
data class ProfilePOJO(@SerializedName("first_name") @Expose var firstName: String,
                       @SerializedName("last_name") @Expose var lastName: String,
                       @SerializedName("phone") @Expose var phone: String,
                       @SerializedName("email") @Expose var email: String,
                       @SerializedName("doc_type") @Expose var docType: String,
                       @SerializedName("doc_number") @Expose var docNumber: String)