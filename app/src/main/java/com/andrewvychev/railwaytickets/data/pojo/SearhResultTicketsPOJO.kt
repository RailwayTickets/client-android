package com.andrewvychev.railwaytickets.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 12/7/17.
 */
class SearhResultTicketsPOJO(@SerializedName("tickets") @Expose var tickets: List<TicketPOJO>)