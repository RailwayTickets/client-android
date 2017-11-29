package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Andrew on 11/30/17.
 */
class TrainPOJO(@SerializedName("from") @Expose var from: String,
                @SerializedName("to") @Expose var to: String,
                @SerializedName("departure") @Expose var departure: String,
                @SerializedName("seat") @Expose var seat: Int,
                @SerializedName("carriage") @Expose var carriage: Int): Serializable {

    override fun toString(): String {
        return "from: $from\nto: $to\ndeparture: $departure\nseat: $seat\ncarriage: $carriage"
    }
}