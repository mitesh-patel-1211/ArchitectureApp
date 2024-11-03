package com.app.architectureapp.rest

import com.app.architectureapp.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProduct(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<ProductResponse>
}