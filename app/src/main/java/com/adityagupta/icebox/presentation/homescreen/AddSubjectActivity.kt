package com.adityagupta.icebox.presentation.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import com.adityagupta.icebox.R
import com.adityagupta.icebox.databinding.ActivityAddSubjectBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddSubjectActivity : AppCompatActivity() {

    lateinit var timePicker: MaterialTimePicker
    lateinit var binding: ActivityAddSubjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Start Time")
                .build()

        binding.addSubjectSelectStartTimeButton.setOnClickListener {
            timePicker.show(supportFragmentManager, "start_time")
        }

        binding.addSubjectSelectEndTImeButton.setOnClickListener {
            timePicker.show(supportFragmentManager, "end_time")
        }



    }
}