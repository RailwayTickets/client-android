package com.andrewvychev.railwaytickets.di.modules

import com.andrewvychev.railwaytickets.RailwayApplication
import com.andrewvychev.railwaytickets.RailwayPreferences
import com.andrewvychev.railwaytickets.data.api.AuthService
import com.andrewvychev.railwaytickets.data.api.RouteService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val application: RailwayApplication) {

    companion object {
        const val BASE_URL = "https://railway-tickets.herokuapp.com/"
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRouteServices(retrofit: Retrofit): RouteService {
        return retrofit.create(RouteService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(preferences: RailwayPreferences): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                    val token = preferences.getToken()
                    if (token.isNotEmpty()) {
                        request.addHeader("token", token)
                    }
                    it.proceed(request.build())
                }
                .build()
    }

    @Provides
    @Singleton
    internal fun providePreferences(): RailwayPreferences {
        return application.appPreferences
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }
}