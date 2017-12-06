package com.andrewvychev.railwaytickets.ui.findroute.models

import ir.mirrajabi.searchdialog.core.Searchable

/**
 * Created by Andrew on 12/6/17.
 */
class FromSearchModel(private val from: String) : Searchable {

    override fun getTitle() = from
}