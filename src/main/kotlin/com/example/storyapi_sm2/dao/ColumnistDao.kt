package com.example.storyapi_sm2.dao

import com.example.storyapi_sm2.domain.entity.Columnist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ColumnistDao: JpaRepository<Columnist, String>  {


}