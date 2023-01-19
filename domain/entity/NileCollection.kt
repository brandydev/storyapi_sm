package com.example.storyapi_sm.domain.entity

import com.example.storyapi_sm.domain.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="collection")
data class NileCollection (
    var slug: String,
    var name: String,
    var baseUri: String,

    /**Platform Data**/
    var ownerName: String?= null,
    var ownerImageUrl: String? = null,
    var imageUrl: String?= null,
    var registeredAt: LocalDateTime?= null,

    ) : BaseEntity(){

    }