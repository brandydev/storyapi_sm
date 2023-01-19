package com.example.storyapi_sm.domain.entity
import com.example.storyapi_sm.domain.base.BaseEntity
import com.example.storyapi_sm.domain.enums.Status
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

@Serializable // 메시지 호출 시 자동으로 직렬화시켜 사용된다.
@Entity
@Table(name="article")
data class Article(
    var slug: String,
): BaseEntity() {

    @Enumerated(EnumType.STRING)
    var status: Status = Status.HIDDEN
    @JsonFormat( // Json 형식 지정
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss"
    )
    var openedAt: LocalDateTime? = null
    var creator: String? = null // article의 main nft 제작자

}