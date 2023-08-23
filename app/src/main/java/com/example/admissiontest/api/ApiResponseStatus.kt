package com.example.admissiontest.api

sealed class ApiResponseStatus<T> {
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Error<T>(val messageId: Int): ApiResponseStatus<T>()
    class Loading<T>: ApiResponseStatus<T>()

}