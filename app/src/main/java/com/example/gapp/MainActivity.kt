package com.example.gapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GalleryViewModel
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ContentResolver instance
        val contentResolver = applicationContext.contentResolver

        // Initialize the ViewModel with the ContentResolver
        viewModel = ViewModelProvider(this, ViewModelFactory(contentResolver)).get(GalleryViewModel::class.java)

        // Observe the result of getAllImages() and set the adapter when the list is available
        viewModel.getAllImages().observe(this) { allPictures ->
            if (allPictures.isNotEmpty()) {
                adapter = ImageAdapter(this, allPictures)
                binding.imageRecycler.adapter = adapter
                binding.recyclerProgres.visibility = View.GONE
            }
        }

        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)
        binding.imageRecycler.setHasFixedSize(true)

        // Storage Permission
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        }

        binding.recyclerProgres.visibility = View.VISIBLE
    }

}
