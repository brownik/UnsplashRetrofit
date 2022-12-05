package com.brownik.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.brownik.unsplash.view.viewmodel.PhotoDataViewModel
import com.brownik.unsplash.databinding.ActivityPhotoBinding
import com.brownik.unsplash.view.adapter.PhotoDataListAdapter

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding
    private val photoDataViewModel: PhotoDataViewModel by viewModels()
    private val photoDataListAdapter: PhotoDataListAdapter by lazy {
        PhotoDataListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.photoRecyclerviewLayer.adapter = photoDataListAdapter

        addOnClickListener()
        addViewModel()
    }

    private fun addOnClickListener() = with(binding){

    }

    private fun addViewModel() {
        photoDataViewModel.photoDataList.observe(this@PhotoActivity, Observer{
            photoDataListAdapter.submitList(it.toMutableList())
        })
    }
}