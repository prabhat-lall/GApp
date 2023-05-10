package com.example.gapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gapp.databinding.ActivityImageFullBinding

class ImageFullActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageFullBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imagePath = intent.getStringExtra("path")
        val imageName = intent.getStringExtra("name")

        supportActionBar?.title = imageName
        Glide.with(this).load(imagePath)
            .into(binding.fullImage)

    }
}