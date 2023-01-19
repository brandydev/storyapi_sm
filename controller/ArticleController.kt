package com.example.storyapi_sm.controller

import com.example.storyapi_sm.domain.entity.Article
import com.example.storyapi_sm.repository.ArticleRepository
import com.example.storyapi_sm.service.ArticleService
import com.example.storyapi_sm.service.FileService
import com.example.util.exception.NileCommonError
import com.example.util.exception.NileException
import com.example.util.response.NileResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/*
@RestController
class ArticleController {

    @Autowired
    lateinit var articleService: ArticleService

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Autowired
    lateinit var articleContentRepository: ArticleContentRepository

    // create
    @PostMapping("/article")
    fun addArticle(
        @RequestBody payload: Article
    ): ResponseEntity<*> {

        if (payload.slug.isBlank()) {
            return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_PARAMETER.getErrorCode(),
                    status = NileCommonError.INVALID_PARAMETER.getHttpStatus(),
                    message = "article 생성을 위해서는 slug 입력이 필요합니다.",
                )
            )
        }

        val createdArticle: Article? = articleService.addArticle(payload)
        createdArticle?.let { return ResponseEntity.ok(
            NileResponse(
                message = "article이 성공적으로 생성되었습니다.",
                result = createdArticle
            )
        ) } ?: return ResponseEntity.ok(
            NileResponse(
                errorCode = NileCommonError.INVALID_SLUG.getErrorCode(),
                status = NileCommonError.INVALID_SLUG.getHttpStatus(),
                message = "이미 존재하는 slug입니다."
            )
        )
    }

    // read
    @GetMapping("/articles")
    fun getArticles(): ResponseEntity<*> {
        val targetArticles =  articleService.getArticles()

        targetArticles.forEach {
            it.contents = mutableListOf()
            it.contents.addAll(articleContentRepository.findAllByArticleId(it.id))
        }

        return ResponseEntity.ok(
            NileResponse(
                result = targetArticles
            )
        )
    }

    @GetMapping("/article")
    fun getArticle(
        @RequestParam("slug", required = false, defaultValue = "") slug: String?
    ): ResponseEntity<*> {
        if (slug.isNullOrBlank()) {
            throw NileException(NileCommonError.INVALID_PARAMETER)
        }

        val targetArticle = articleService.getArticleBySlug(slug)
        val targetArticleContents = articleContentRepository.findAllByArticleId(targetArticle.id)

        targetArticle.contents = mutableListOf()
        targetArticle.contents.addAll(targetArticleContents)

        return ResponseEntity.ok(
            NileResponse(
                result = targetArticle
            )
        )
    }

    // update
    @PutMapping("/article")
    fun updateArticle(
        @RequestParam("slug", required = false, defaultValue = "") slug: String?,
        @RequestBody request: Article
    ): ResponseEntity<*> {
        if (slug.isNullOrBlank()) {
            throw NileException(NileCommonError.INVALID_PARAMETER)
        }

        val targetArticle = articleService.getArticleBySlug(slug)

        targetArticle.slug = request.slug
        targetArticle.status = request.status
        targetArticle.openedAt = request.openedAt // openedAt 지정하지 않으면, 현재 시점으로 기본 설정
        targetArticle.nftCreator = request.nftCreator

        articleService.addArticle(targetArticle)

        // content update
        val prevContents = articleContentRepository.findAllByArticleId(targetArticle.id)
        prevContents.forEach {
            articleContentRepository.delete(it) // 삭제 후 재등록 말고 수정으로!
            // postgres는 update 시 기존 거를 지우고 다시 만듦
            // update 관련 글 찾아보기
        }
        targetArticle.contents.forEach {
            articleContentRepository.save(ArticleContent(it.language, it.title, it.description, it.content, targetArticle.id))
        }

        return ResponseEntity.ok().build<Any>()
    }

    // delete
    // 조회를 제외하고는 권한 처리
    @DeleteMapping("/article")
    fun removeArticle(
        @RequestParam("slug", required = false, defaultValue = "") slug: String?
    ): ResponseEntity<*> {
        if (slug.isNullOrBlank()) {
            throw NileException(NileCommonError.INVALID_PARAMETER)
        }

        val targetArticle: Article = articleService.getArticleBySlug(slug)
        articleService.removeArticleBySlug(targetArticle.slug)

        val targetArticleContents = articleContentRepository.findAllByArticleId(targetArticle.id)
        targetArticleContents.forEach {
            articleContentRepository.delete(it)
        }

        return ResponseEntity.ok().build<Any>()
    }
}
 */