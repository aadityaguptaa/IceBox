package com.adityagupta.icebox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adityagupta.icebox.databinding.ActivityMainBinding
import com.adityagupta.icebox.presentation.homescreen.HomeScreenActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeScreenButton.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }

    }
}