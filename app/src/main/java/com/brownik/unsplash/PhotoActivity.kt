package com.brownik.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.brownik.unsplash.data.api.PhotoRetrofitClient
import com.brownik.unsplash.databinding.ActivityPhotoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val photoRetrofitClient = PhotoRetrofitClient.getPhotoDataInstance()
        CoroutineScope(Dispatchers.IO).launch {
            val response = photoRetrofitClient.getPhotoData()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("qwe123", it[0].id)
                }
            } else {
                Log.d("qwe123", "error: ${response.message()}")
            }
        }
    }
}