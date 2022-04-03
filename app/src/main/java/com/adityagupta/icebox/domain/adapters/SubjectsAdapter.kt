package com.adityagupta.icebox.domain.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adityagupta.icebox.data.database.Subject
import com.adityagupta.icebox.databinding.SubjectItemLayoutBinding
import java.text.SimpleDateFormat

class SubjectsAdapter(val context: Context): RecyclerView.Adapter<SubjectsAdapter.MyViewHolder>() {



    inner class MyViewHolder(val binding: SubjectItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
        }

    }

    override fun getItemCount() = subjects.size


}