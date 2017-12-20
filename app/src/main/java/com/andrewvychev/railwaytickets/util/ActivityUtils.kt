package com.andrewvychev.railwaytickets.util

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View


/**
 * Created by Andrew on 12/21/17.
 */
fun Activity.showSnackbar(text: String) {
    val root = findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(root, text, Snackbar.LENGTH_LONG)
    snackbar.setAction(getString(android.R.string.ok)) {
        snackbar.dismiss()
    }
    snackbar.show()
}