package com.example.storyapi_sm.util.exception

interface NileError {
        fun getErrorCode(): Int
        fun getErrorMessage(): String?

}