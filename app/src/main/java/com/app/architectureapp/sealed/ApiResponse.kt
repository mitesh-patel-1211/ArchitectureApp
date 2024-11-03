package com.app.architectureapp.sealed

sealed class ApiResponse<T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Failure<T>(val msg: String) : ApiResponse<T>()
    class Loading<T> : ApiResponse<T>()
}