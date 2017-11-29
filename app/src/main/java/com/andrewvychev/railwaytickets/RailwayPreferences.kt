package com.andrewvychev.railwaytickets

import android.app.Application
import android.content.Context

/**
 * Created by Andrew on 11/29/17.
 */
class RailwayPreferences(application: Application) {

    companion object {
        const val PREF_NAME = "railway_pref"
        const val KEY_TOKEN = "token"
    }

    private val preferences = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getToken(): String {
        return preferences.getString(KEY_TOKEN, "")
    }

    fun setToken(token: String) {
        preferences.edit()
                .putString(KEY_TOKEN, token)
                .apply()
    }

}