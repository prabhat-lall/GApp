package com.example.gapp

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel(contentResolver: ContentResolver) : ViewModel() {

    private var galleryRepository : GalleryRepository = GalleryRepository(contentResolver)


    fun getAllImages(): LiveData<List<Image>> {
        val imagesLiveData = MutableLiveData<List<Image>>()
        val images = galleryRepository.getAllImages()
        imagesLiveData.value = images
        return imagesLiveData
    }
}