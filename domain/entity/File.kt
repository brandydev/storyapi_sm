package com.example.storyapi_sm.domain.entity

import com.example.storyapi_sm.domain.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kotlinx.serialization.Serializable

@Serializable // 메시지 호출 시 자동으로 직렬화시켜 사용된다.
@Entity
@Table(name="file")
data class File (
    var fileType: String,
    var name: String,
    var path: String, // azure file storage path
    var sizeType: String, // rectangle로 통일
    var location: String, // thumbnail/content/top/banner(only image)
) : BaseEntity() {

}