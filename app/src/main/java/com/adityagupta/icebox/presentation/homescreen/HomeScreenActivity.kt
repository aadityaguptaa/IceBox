package com.adityagupta.icebox.presentation.homescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adityagupta.icebox.R
import com.adityagupta.icebox.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeScreenAddSubjectCardView.setOnClickListener {
            startActivity(Intent(this, AddSubjectActivity::class.java))

        }
    }
}