package com.brownik.unsplash.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brownik.unsplash.R
import com.brownik.unsplash.data.api.PhotoRetrofitApi
import com.brownik.unsplash.data.api.PhotoRetrofitApiState
import com.brownik.unsplash.data.api.PhotoRetrofitClient
import com.brownik.unsplash.data.api.PhotoRetrofitRepository
import com.brownik.unsplash.data.model.PhotoData
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PhotoDataViewModel(
    private val photoRetrofitRepository: PhotoRetrofitRepository
) : ViewModel() {

    private var _photoRepositories: MutableStateFlow<PhotoRetrofitApiState<ArrayList<PhotoData>>> =
        MutableStateFlow(PhotoRetrofitApiState.Loading())
    val photoRepositories: StateFlow<PhotoRetrofitApiState<ArrayList<PhotoData>>> = _photoRepositories

/*    private var _photoDataList = MutableLiveData<ArrayList<PhotoData>>()
    val photoDataList = _photoDataList*/

    init {
        /*val photoRetrofitClient = PhotoRetrofitClient.getPhotoDataInstance()
        CoroutineScope(Dispatchers.IO).launch {
            val response = photoRetrofitClient.getPhotoData()
            if (response.isSuccessful) {
                response.body()?.let {
                    _photoDataList.postValue(it)
                }
            } else {
                Log.d("qwe123", "PhotoDataViewModel.init Error: ${response.message()}")
            }
        }*/
        requestPhotoRepositories()
    }

    private fun requestPhotoRepositories() {
        photoRetrofitRepository.getRepositories()
            .onStart {
                _photoRepositories.value = PhotoRetrofitApiState.Loading()
            }.onEach {
                _photoRepositories.value = it
            }.catch { error ->
                _photoRepositories.value = PhotoRetrofitApiState.Error("${error.message}")
            }.launchIn(viewModelScope)
    }

    fun imageCaching(context: Context) {
        _photoRepositories.value.data?.let { list ->
            list.forEach { data ->
                Glide.with(context)
                    .load(data.urls.raw)
                    .error(R.drawable.ic_launcher_foreground)
                    .preload()
            }
        }
    }
}