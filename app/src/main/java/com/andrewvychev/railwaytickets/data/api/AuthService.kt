package com.andrewvychev.railwaytickets.data.api


import com.andrewvychev.railwaytickets.data.pojo.LoginPOJO
import com.andrewvychev.railwaytickets.data.pojo.ProfilePOJO
import com.andrewvychev.railwaytickets.data.pojo.RegisterPOJO
import com.andrewvychev.railwaytickets.data.pojo.TokenPOJO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Completable
import rx.Observable

/**
 * Created by Siarhei Hvazdou on 12.07.2017.
 */
interface AuthService {

    @POST("login")
    fun login(@Body login: LoginPOJO): Observable<TokenPOJO>

    @POST("register")
    fun register(@Body register: RegisterPOJO): Observable<TokenPOJO>

    @POST("profile")
    fun saveProfile(@Body profile: ProfilePOJO): Completable

    @GET("profile")
    fun getProfile(): Observable<ProfilePOJO>
}