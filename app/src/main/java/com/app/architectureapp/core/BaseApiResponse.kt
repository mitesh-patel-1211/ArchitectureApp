package com.app.architectureapp.core

import com.app.architectureapp.model.ErrorResponse
import com.app.architectureapp.sealed.ApiResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResponse.Success(body)
                }
            }
            return when (response.code()) {
                400 -> error(
                    try {
                        val errorBody = response.errorBody()?.string() ?: ""
                        val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message ?: ""
                    } catch (e: Exception) {
                        "Error parsing error body"
                    }
                )

                401 -> error("Unauthorized")
                else -> error("Something went wrong")
            }
        } catch (throwable: Throwable) {
            return error(errorMessage = throwable.message ?: throwable.toString())
        }
    }


    private fun <T> error(
        errorMessage: String?
    ): ApiResponse<T> =
        ApiResponse.Failure("$errorMessage")
}