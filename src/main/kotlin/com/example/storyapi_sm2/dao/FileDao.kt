package com.example.storyapi_sm2.dao

import com.example.storyapi_sm2.domain.entity.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileDao: JpaRepository<File, String> {

}