package com.adityagupta.icebox.domain.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.text.format.DateFormat.is24HourFormat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityagupta.icebox.data.database.Subject
import com.adityagupta.icebox.databinding.SubjectItemLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class SubjectsAdapter(val context: Context): RecyclerView.Adapter<SubjectsAdapter.MyViewHolder>() {



    inner class MyViewHolder(val binding: SubjectItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.deleteSubjectIcon.setOnClickListener {
                    createDeleteDialog()
                }
            }

        private fun createDeleteDialog() {
            MaterialAlertDialogBuilder(context)
                .setTitle("Delete Subject")
                .setMessage("All the data related to this subject will be lost forever. Do you still want to continue?")
                .setPositiveButton("No") { dialog, which ->
                    // Respond to neutral button press
                }
                .setNegativeButton("Yes") { dialog, which ->
                    // Respond to negative button press
                }
                .show()
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.subjectId == newItem.subjectId
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var subjects: List<Subject>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SubjectItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val subject = subjects[position]
            subjectName.text = subject.subjectName
            itemViewSubjectStartTime.text = "Start Time: ${subject.startingTime}"
            itemViewSubjectEndTime.text ="End Time: ${subject.endingTime}"
            itemViewSubjectStartChip.text = SimpleDateFormat("dd/MM/yyyy").format(Date(subject.startingDate!!))
        }

    }

    override fun getItemCount() = subjects.size


}