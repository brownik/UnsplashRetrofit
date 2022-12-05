package com.brownik.unsplash.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brownik.unsplash.data.api.PhotoRetrofitClient
import com.brownik.unsplash.data.model.PhotoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoDataViewModel: ViewModel() {
    private var _photoDataList = MutableLiveData<ArrayList<PhotoData>>()
    val photoDataList = _photoDataList

    init {
        val photoRetrofitClient = PhotoRetrofitClient.getPhotoDataInstance()
        CoroutineScope(Dispatchers.IO).launch {
            val response = photoRetrofitClient.getPhotoData()
            if (response.isSuccessful) {
                response.body()?.let {
                    _photoDataList.postValue(it)
                }
            } else {
                Log.d("qwe123", "PhotoDataViewModel.init Error: ${response.message()}")
            }
        }
    }
}