package com.example.storyapi_sm.domain.enums

enum class Status { // 접근 여부
    OPEN, // everyone can access
    HIDDEN, // only admin & db
    ARCHIVED // only db
}