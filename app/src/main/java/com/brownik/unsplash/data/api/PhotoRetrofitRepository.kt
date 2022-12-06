package com.brownik.unsplash.data.api

import com.brownik.unsplash.data.model.PhotoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class PhotoRetrofitRepository {
    fun getRepositories(): Flow<PhotoRetrofitApiState<ArrayList<PhotoData>>> =
        flow {
            try {
                val photoRetrofitClient = PhotoRetrofitClient.getPhotoDataInstance()
                val response = photoRetrofitClient.getPhotoData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(PhotoRetrofitApiState.Success(it))
                    }
                } else {
                    try {
                        emit(PhotoRetrofitApiState.Error(response.errorBody()!!.string()))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                emit(PhotoRetrofitApiState.Error(e.message ?: ""))
            } as Unit
        }.flowOn(Dispatchers.IO)
}