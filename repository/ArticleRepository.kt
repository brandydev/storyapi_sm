package com.example.storyapi_sm.repository

import com.example.storyapi_sm.domain.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.awt.print.PageFormat
import java.util.*

@Repository
interface ArticleRepository: JpaRepository<Article, UUID> {
    fun findAllByIdIsNotNull(): List<Article>
    fun findAllByIdIsNotNull(pageable: Pageable): Page<Article>
    fun findAllByIdIsNotNullOrderByOpenedAtDesc():List<Article>
    fun findAllByIdIsNotNullOrderByOpenedAtDesc(pageable: Pageable): Page<Article>
    fun findBySlug(slug: String): Article?
}