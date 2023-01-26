package com.example.storyapi_sm2.repository

import com.example.storyapi_sm2.domain.entity.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FileRepository: JpaRepository<File, UUID> {
    fun existsByName(name: String?): Boolean
    /*
    fun findAllByIdIsNotNull(): List<File>
    fun findByAllFileId(fileId: UUID?): List<File>
    fun findByPath(path: String): String
    fun deleteByPath(path: String)*/
    fun addFileByName(name: String?): String
    fun getFileByName(name: String?): String
    fun updateFileByName(name: String?): String
    fun deleteFileByName(name: String?): String
    fun findByName(name: String?): File

}