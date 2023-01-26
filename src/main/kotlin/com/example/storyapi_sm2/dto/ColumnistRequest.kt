package com.example.storyapi_sm2.dto

data class ColumnistRequest (
    //id는 빼고 @field:NotBlank -> 빈값으로 들어오면 안된다
    val name: String,
    val info: String,
    val profileImage: String
)