package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 12/6/17.
 */
class LocationsPOJO(@SerializedName("locations") @Expose var locations: List<String>)