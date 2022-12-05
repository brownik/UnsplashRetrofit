package com.brownik.unsplash.data.api

import com.brownik.unsplash.data.model.PhotoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoRetrofitApi {
    @GET("photos")
    suspend fun getPhotoData(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("order_by") orderBy: String = "latest"
    ): Response<ArrayList<PhotoData>>
}