package com.andrewvychev.railwaytickets.ui.findroute.models

import ir.mirrajabi.searchdialog.core.Searchable

/**
 * Created by Andrew on 12/6/17.
 */
class ToSearchModel(private val to: String) : Searchable {

    override fun getTitle() = to
}