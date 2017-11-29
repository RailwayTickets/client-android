package com.andrewvychev.railwaytickets.ui.findroute

/**
 * Created by Andrew on 11/29/17.
 */

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import org.threeten.bp.LocalDate

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {

        private const val ARG_DATE = "date"

        fun newInstance(date: LocalDate): DatePickerFragment {
            val args = Bundle(1)
            args.putSerializable(ARG_DATE, date)
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.arguments = args
            return datePickerFragment
        }
    }

    private var listener: Listener? = null


    fun setListener(listener: Listener) {
        this.listener = listener
    }


//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (parentFragment is Listener) {
//            listener = parentFragment as? Listener
//        } else {
//            throw ClassCastException(parentFragment.toString() + " must implement Listener");
//        }
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as? LocalDate ?: LocalDate.now()


        val datePickerDialog = DatePickerDialog(
                activity,
                this,
                date.year,
                date.month.minus(1).value,
                date.dayOfMonth
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val date = LocalDate.of(year, month, day)
        listener?.onDateSet(date)
    }

    interface Listener {

        fun onDateSet(date: LocalDate)

    }
}
