package com.adityagupta.icebox.presentation.homescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.adityagupta.icebox.R
import com.adityagupta.icebox.databinding.ActivityHomeScreenBinding
import com.adityagupta.icebox.domain.adapters.SubjectsAdapter
import com.adityagupta.icebox.domain.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding

    @Inject
    @Named("room")
    lateinit var database: AppDatabase

    var adapter = SubjectsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val subjects = database.dao().getSubjects()
            adapter.subjects = subjects
            binding.homeScreenSubjectsRecyclerView.adapter = adapter
        }

        binding.homeScreenAddSubjectCardView.setOnClickListener {
            startActivity(Intent(this, AddSubjectActivity::class.java))
        }
    }
}