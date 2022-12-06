package com.brownik.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.brownik.unsplash.data.api.PhotoRetrofitApiState
import com.brownik.unsplash.data.api.PhotoRetrofitRepository
import com.brownik.unsplash.data.model.PhotoData
import com.brownik.unsplash.view.viewmodel.PhotoDataViewModel
import com.brownik.unsplash.databinding.ActivityPhotoBinding
import com.brownik.unsplash.view.adapter.PhotoDataListAdapter
import com.brownik.unsplash.view.viewmodlefactory.PhotoDataViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding
    private lateinit var photoDataViewModel: PhotoDataViewModel
    private lateinit var photoDataViewModelFactory: PhotoDataViewModelFactory
    private val photoDataListAdapter: PhotoDataListAdapter by lazy {
        PhotoDataListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.photoRecyclerviewLayer.adapter = photoDataListAdapter
        addOnClickListener()

        photoDataViewModelFactory = PhotoDataViewModelFactory(PhotoRetrofitRepository())
        photoDataViewModel = ViewModelProvider(
            this,
            photoDataViewModelFactory
        )[PhotoDataViewModel::class.java]
        addViewModel()
    }

    private fun addOnClickListener() = with(binding) {

    }

    private fun addViewModel() {
        /*photoDataViewModel.photoDataList.observe(this@PhotoActivity, Observer {
            photoDataListAdapter.submitList(it.toMutableList())
        })*/
        lifecycleScope.launchWhenStarted {
            photoDataViewModel.photoRepositories.collectLatest { state ->
                when (state) {
                    is PhotoRetrofitApiState.Loading -> {

                    }
                    is PhotoRetrofitApiState.Success -> {
                        state.data?.let { photoDataListAdapter.submitList(it.toMutableList()) }

                    }
                    is PhotoRetrofitApiState.Error -> {
                        state.data?.let {  }
                    }
                }
            }
        }
    }
}