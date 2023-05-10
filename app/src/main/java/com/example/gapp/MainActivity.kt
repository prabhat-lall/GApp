package com.example.gapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private var allPictures: ArrayList<Image>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageRecycler.layoutManager = GridLayoutManager(this, 3)
        binding.imageRecycler.setHasFixedSize(true)


        //Storage Permission
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

        allPictures = ArrayList()

        if (allPictures!!.isEmpty()) {
            binding.recyclerProgres.visibility = View.VISIBLE
            allPictures = getAllImages()
            binding.imageRecycler.adapter = ImageAdapter(this, allPictures!!)
            binding.recyclerProgres.visibility = View.GONE

        }

    }

    private fun getAllImages(): ArrayList<Image>? {
        val images = ArrayList<Image>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DISPLAY_NAME
        )

        var cursor = this.contentResolver.query(allImageUri, projection, null, null, null)

        try {
            cursor!!.moveToFirst()
            do {
                val image = Image(
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                )
                images.add(image)
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }
}