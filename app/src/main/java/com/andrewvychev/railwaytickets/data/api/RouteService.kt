package com.andrewvychev.railwaytickets.data.api

import com.andrewvychev.railwaytickets.data.pojo.LocationsPOJO
import com.andrewvychev.railwaytickets.data.pojo.SearchPOJO
import com.andrewvychev.railwaytickets.data.pojo.SearchResultPOJO
import com.andrewvychev.railwaytickets.data.pojo.SearhResultTicketsPOJO
import com.andrewvychev.railwaytickets.data.pojo.TicketPOJO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Completable
import rx.Observable

/**
 * Created by Andrew on 11/30/17.
 */
interface RouteService {

    @POST("search")
    fun seach(@Body search: SearchPOJO): Observable<SearchResultPOJO>

    @GET("directions")
    fun directions(): Observable<LocationsPOJO>

    @GET("departures")
    fun departures(): Observable<LocationsPOJO>

    @GET("buy")
    fun buy(@Query("id") id: String): Completable

    @GET("profile/tickets")
    fun myTickets(): Observable<SearhResultTicketsPOJO>
}