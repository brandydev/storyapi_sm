package com.example.storyapi_sm2.repository

import com.example.storyapi_sm2.domain.entity.Columnist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ColumnistRepository: JpaRepository<Columnist, UUID> {
    fun findByColumnistId(columnistId: UUID?): Boolean
    fun existsByName(name: String?): Boolean
    fun findByName(name: String?): Columnist
    fun addColumnistByName(name: String?): String
    fun getColumnistByName(name: String?): String
    //fun updateColumnistByName(name: String?): String
    //fun removeColumnistByName(name: String?): Columnist
    fun deleteColumnistByName(name: String?): String

}