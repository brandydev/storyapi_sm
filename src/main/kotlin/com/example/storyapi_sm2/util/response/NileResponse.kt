package com.example.storyapi_sm2.util.response

data class NileResponse(
    val errorCode: Int?=0,
    val status: Int?= 200,
    val message: String? = "OK",
    val result: Any? = null
)