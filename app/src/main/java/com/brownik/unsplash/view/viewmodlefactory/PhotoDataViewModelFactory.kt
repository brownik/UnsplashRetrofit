package com.brownik.unsplash.view.viewmodlefactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brownik.unsplash.data.api.PhotoRetrofitRepository

class PhotoDataViewModelFactory(
    private val photoRetrofitRepository: PhotoRetrofitRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PhotoRetrofitRepository::class.java).newInstance(photoRetrofitRepository)
    }
}