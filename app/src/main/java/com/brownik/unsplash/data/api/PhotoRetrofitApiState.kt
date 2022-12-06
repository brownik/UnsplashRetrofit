package com.brownik.unsplash.data.api

sealed class PhotoRetrofitApiState<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : PhotoRetrofitApiState<T>(data)
    class Error<T>(message: String, data: T? = null) : PhotoRetrofitApiState<T>(data, message)
    class Loading<T> : PhotoRetrofitApiState<T>()
}