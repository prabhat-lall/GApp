package com.example.gapp

import android.content.ContentResolver
import android.provider.MediaStore

class GalleryRepository(private val contentResolver: ContentResolver) {

    fun getAllImages(): ArrayList<Image> {
        val images = ArrayList<Image>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DISPLAY_NAME
        )

        val cursor = contentResolver.query(allImageUri, projection, null, null, null)

        cursor?.use { cursor ->
            val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val imagePath = cursor.getString(dataIndex)
                val imageName = cursor.getString(nameIndex)
                val image = Image(imagePath, imageName)
                images.add(image)
            }
        }

        return images
    }
}