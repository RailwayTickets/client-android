package com.andrewvychev.railwaytickets

import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView

import java.util.ArrayList
import java.util.Collections

/**
 * Created by Andrew on 6/30/17.
 */
abstract class ArrayRecyclerAdapter<E, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH> {

    protected val items: MutableList<E>

    constructor() {
        items = ArrayList()
    }

    constructor(items: Collection<E>) {
        this.items = ArrayList(items)
    }

    constructor(@IntRange(from = 0) capacity: Int) {
        items = ArrayList(capacity)
    }

    @IntRange(from = 0)
    override fun getItemCount(): Int {
        return items.size
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun add(item: E) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun add(position: Int, item: E) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun addAll(items: Collection<E>) {
        if (!items.isEmpty()) {
            val start = this.items.size
            this.items.addAll(items)
            notifyItemRangeInserted(start, items.size)
        }
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    operator fun set(position: Int, item: E) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun replaceAll(items: Collection<E>) {
        if (items.isEmpty() && this.items.isEmpty()) {
            return
        }

        this.items.clear()
        notifyDataSetChanged()
        addAll(items)
    }

    fun removeAll() {
        if (items.isEmpty()) {
            return
        }

        items.clear()
        notifyDataSetChanged()
    }

    fun indexOf(item: E): Int {
        return items.indexOf(item)
    }

    fun getItem(position: Int): E {
        return items[position]
    }

    fun lastItem(): E? {
        return items.lastOrNull()
    }

    fun all(): List<E> {
        return Collections.unmodifiableList(items)
    }
}

