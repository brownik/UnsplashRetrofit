package com.brownik.unsplash.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoRetrofitClient {

    private const val baseUrl = "https://api.unsplash.com/"
    private const val userName = "Authorization"
    private const val userId = "Client-ID Y-iXWMQD5Vrkoen8rR35pQCAluJnohWVi0k95fG8kUk"

    fun getPhotoDataInstance(): PhotoRetrofitApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().apply {
                    addHeader(userName, userId)
                }.build()
                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PhotoRetrofitApi::class.java)
    }
}