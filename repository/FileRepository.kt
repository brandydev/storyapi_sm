package com.example.storyapi_sm.repository

import com.example.storyapi_sm.domain.entity.File
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FileRepository : JpaRepository<File,UUID> {
    fun findAllByFileIdIsNotNull(): List<File>
    fun findAllByFileIdIsNotNull(pageable: Pageable): Page<File>
    fun findAddressByStorage(text: String): File

}