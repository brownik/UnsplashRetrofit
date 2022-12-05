package com.brownik.unsplash.view.viewmodlefactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brownik.unsplash.view.viewmodel.PhotoDataViewModel

class PhotoDataViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PhotoDataViewModel::class.java)) {
            PhotoDataViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}