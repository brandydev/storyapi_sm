package com.example.storyapi_sm2.domain.entity

import com.example.storyapi_sm2.domain.base.BaseEntity
import jakarta.persistence.Entity

@Entity
data class Columnist (
    //var columnistId: String,
    var name: String, // var name: String=""
    var info: String?,
    var profileImage: String?

) : BaseEntity() {

}