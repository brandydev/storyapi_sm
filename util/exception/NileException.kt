package com.example.storyapi_sm.util.exception

import com.example.storyapi_sm.util.exception.NileError
import com.example.storyapi_sm.util.response.NileResponse


class NileException (nileError: NileError): RuntimeException() {
    private val errorCode: Int = nileError.getErrorCode()
    private val errorMessage: String? = nileError.getErrorMessage()
    private val statusCode: Int? = 200

    fun getStatusCode() = statusCode
    fun toResponse() = NileResponse(
        errorCode,
        statusCode,
        errorMessage ?: ""
    )
}