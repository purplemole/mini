package com.clobot.mini.view.common

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.NumberPicker
import android.widget.TimePicker
import java.lang.reflect.Field
import java.util.ArrayList

class CustomTimePickerDialog(
    context: Context?,
    listener: OnTimeSetListener?,
    hourOfDay: Int,
    minute: Int,
    is24HourView: Boolean
) :
    TimePickerDialog(
        context,
        TimePickerDialog.THEME_HOLO_LIGHT,
//        android.R.style.Theme_Holo_Light_Dialog,
        null,
        hourOfDay,
        minute / TIME_PICKER_INTERVAL, is24HourView
    ) {
    private var mTimePicker: TimePicker? = null
    private val mTimeSetListener: OnTimeSetListener?

    init {
        mTimeSetListener = listener
    }

    override fun updateTime(hourOfDay: Int, minuteOfHour: Int) {
        mTimePicker!!.currentHour = hourOfDay
        mTimePicker!!.currentMinute = minuteOfHour / TIME_PICKER_INTERVAL
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            BUTTON_POSITIVE -> mTimeSetListener?.onTimeSet(
                mTimePicker,
                mTimePicker!!.currentHour,
                mTimePicker!!.currentMinute * TIME_PICKER_INTERVAL
            )
            BUTTON_NEGATIVE -> cancel()
        }
    }

    @SuppressLint("PrivateApi")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        try {
            val classForid = Class.forName("com.android.internal.R\$id")
            val timePickerField: Field = classForid.getField("timePicker")
            mTimePicker = findViewById(timePickerField.getInt(null))
            val field: Field = classForid.getField("minute")
            val minuteSpinner = mTimePicker?.findViewById(field.getInt(null)) as NumberPicker
            minuteSpinner.minValue = 0
            minuteSpinner.maxValue = 60 / TIME_PICKER_INTERVAL - 1
            val displayedValues: MutableList<String> = ArrayList()
            var i = 0
            while (i < 60) {
                displayedValues.add(String.format("%02d", i))
                i += TIME_PICKER_INTERVAL
            }
            minuteSpinner.displayedValues = displayedValues
                .toTypedArray()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TIME_PICKER_INTERVAL = 5
    }
}