package com.example.admissiontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admissiontest.databinding.ActivityMainBinding
import com.example.admissiontest.ui.productlist.ProductListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            startActivity(Intent(this, ProductListActivity::class.java))
        }
    }
}