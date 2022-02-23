package com.adityagupta.icebox.presentation.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import com.adityagupta.icebox.R
import com.adityagupta.icebox.databinding.ActivityAddSubjectBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddSubjectActivity : AppCompatActivity() {

    lateinit var startTimePicker: MaterialTimePicker
    lateinit var endTimePicker: MaterialTimePicker

    lateinit var binding: ActivityAddSubjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartEndTimePicker()

    }

    private fun setStartEndTimePicker() {

        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        startTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Start Time")
                .build()

        endTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)

                .setTitleText("Start Time")
                .build()

        binding.addSubjectSelectStartTimeButton.setOnClickListener {
            startTimePicker.show(supportFragmentManager, "start_time")
            timeSelectedListener(startTimePicker, "start");
        }

        binding.addSubjectSelectEndTImeButton.setOnClickListener {
            endTimePicker.show(supportFragmentManager, "end_time")
            timeSelectedListener(endTimePicker, "end");
        }
    }

    private fun timeSelectedListener(picker: MaterialTimePicker, tag: String) {

        picker.addOnPositiveButtonClickListener {
            if(tag == "start"){
                binding.addSubjectStartTimeEditText.editText?.setText("${picker.hour}:${picker.minute}")
            }else{
                binding.addSubjectEndTimeEditText.editText?.setText("${picker.hour}:${picker.minute}")
            }
            // call back code
        }
        picker.addOnNegativeButtonClickListener {
            // call back code
        }
        picker.addOnCancelListener {
            // call back code
        }
        picker.addOnDismissListener {
            // call back code
        }
    }


}