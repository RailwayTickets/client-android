package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 11/30/17.
 */
data class SearchPOJO(@SerializedName("from") @Expose var from: String,
                      @SerializedName("to") @Expose var to: String,
                      @SerializedName("date") @Expose var date: String)