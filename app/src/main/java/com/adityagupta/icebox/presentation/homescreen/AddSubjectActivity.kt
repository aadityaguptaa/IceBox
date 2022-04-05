package com.adityagupta.icebox.presentation.homescreen

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adityagupta.icebox.data.database.Subject
import com.adityagupta.icebox.databinding.ActivityAddSubjectBinding
import com.adityagupta.icebox.domain.database.AppDatabase
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class AddSubjectActivity : AppCompatActivity() {

    lateinit var startTimePicker: MaterialTimePicker
    lateinit var endTimePicker: MaterialTimePicker
    val weekDays: List<String> =  listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    var startTime = ""
    var endTime = ""
    var startDate: Long = 0
    lateinit var binding: ActivityAddSubjectBinding

    @Inject
    @Named("room")
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartEndTimePicker()
        setDatePicker()
        setInsertSubjectListener()


    }

    private fun setInsertSubjectListener() {
        binding.addSubjectSaveButton.setOnClickListener {
            if(checkFields()){
                insertSubject()
            }else{
                // TODO
            }
        }
    }

    private fun checkFields(): Boolean {
        return !(startTime.isEmpty() || endTime.isEmpty() || startDate.equals(0))
    }

    private fun insertSubject() {
        val subjectName = binding.addSubjectNameEditText.text.toString()

        val selectedDays = getSelectedDays()
        val tempSubject: Subject = Subject(null, subjectName = subjectName, startTime, endTime, startDate, selectedDays)

        lifecycleScope.launch {
            database.dao().addSubject(tempSubject)
            startActivity(Intent(applicationContext, HomeScreenActivity::class.java))

        }


    }

    private fun getSelectedDays(): String {
        val ids = binding.chipGroup.checkedChipIds
        val selectedDays: MutableList<Int> = mutableListOf()
        for( i in ids){
            val chip : Chip = binding.chipGroup.findViewById(i)
            selectedDays.add(weekDays.indexOf(chip.text))
        }
        return selectedDays.joinToString(separator = ",")
    }

    private fun setDatePicker() {
        val picker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.addSubjectSelectDateButton.setOnClickListener {
            picker.show(supportFragmentManager, "date")
            picker.addOnPositiveButtonClickListener {
                startDate = it
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
                startTime = "${picker.hour}:${picker.minute}"
                binding.addSubjectStartTimeEditText.editText?.setText(startTime)
            }else{
                endTime = "${picker.hour}:${picker.minute}"
                binding.addSubjectEndTimeEditText.editText?.setText(endTime)
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