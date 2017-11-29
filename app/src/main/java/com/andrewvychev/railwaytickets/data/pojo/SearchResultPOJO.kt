package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 11/30/17.
 */
data class SearchResultPOJO(@SerializedName("tickets") @Expose var trains: List<TrainPOJO>)