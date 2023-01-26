package com.example.storyapi_sm2.domain.entity

import com.example.storyapi_sm2.domain.base.BaseEntity
import com.example.storyapi_sm2.enums.Location
import com.example.storyapi_sm2.enums.Types
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.*

@Entity
data class File (
    var articleId: UUID?, //파일을 저장하므로 따로 저장
    //var fileId:String?, // 필수, UUID?
    @Enumerated(EnumType.STRING)
    var fileType: Types?, // enums 내부 데이터의 type을 갖도록
    var name: String,
    var path: String?, // 필수
    var sizeType: String?,
    @Enumerated(EnumType.STRING)
    var location: Location?

) : BaseEntity() {

}