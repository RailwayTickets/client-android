package com.andrewvychev.railwaytickets.base

/**
 * Created by Andrew on 7/6/17.
 */
interface Contract {

    interface Model

    interface Presenter<V : View> {

        /**
         * Invokes when presenter was created and needed to attach the view to work with
         * @param view #SongbookContract.View instance
         * */
        fun attachView(view: V)

        /**
         * Invokes when view is ready to destroy and presenter should perform all
         * necessary cleaning operations
         * */
        fun detachView()

        /**
         * Method to get view
         * @return View
         */
        fun getView(): V?

    }

    interface View

    interface AdapterView<E> : View {

        /**
         * Used for add items to adapter
         * @param items
         */
        fun addItems(items: Collection<E>)

        /**
         * Used for replace items in adapter. For example when use swipe to refresh.
         * @param items
         */
        fun replaceItems(items: Collection<E>)

    }
}