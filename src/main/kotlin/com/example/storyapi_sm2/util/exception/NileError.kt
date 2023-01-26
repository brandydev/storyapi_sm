package com.example.storyapi_sm2.util.exception

interface NileError {

        fun getErrorCode(): Int
        fun getErrorMessage(): String?
        fun getHttpStatus(): Int
    }

