package com.adityagupta.icebox.presentation.homescreen

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.appcompat.app.AppCompatActivity
import com.adityagupta.icebox.databinding.ActivityAddSubjectBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class AddSubjectActivity : AppCompatActivity() {

    lateinit var startTimePicker: MaterialTimePicker
    lateinit var endTimePicker: MaterialTimePicker

    lateinit var binding: ActivityAddSubjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartEndTimePicker()
        setDatePicker()

    }

    private fun setDatePicker() {
        val picker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.addSubjectSelectDateButton.setOnClickListener {
            picker.show(supportFragmentManager, "date")
            picker.addOnPositiveButtonClickListener {
                val dateString: String =
                    SimpleDateFormat("dd/MM/yyyy").format(Date(it))

                binding.addSubjectSelectDateTextView.text = dateString
            }
            picker.addOnNegativeButtonClickListener {
                // Respond to negative button click.
            }
            picker.addOnCancelListener {
                // Respond to cancel button click.
            }
            picker.addOnDismissListener {
                // Respond to dismiss events.
            }
        }
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