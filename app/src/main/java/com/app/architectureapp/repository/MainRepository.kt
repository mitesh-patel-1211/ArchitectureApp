package com.app.architectureapp.repository

import com.app.architectureapp.core.BaseApiResponse
import com.app.architectureapp.model.ProductResponse
import com.app.architectureapp.rest.ApiService
import com.app.architectureapp.sealed.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : BaseApiResponse() {
    fun getProduct(limit: Int, skip: Int): Flow<ApiResponse<ProductResponse>> = flow {
        emit(safeApiCall {
            apiService.getProduct(limit, skip)
        })
    }
}