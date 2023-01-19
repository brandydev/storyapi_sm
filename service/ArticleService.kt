package com.example.storyapi_sm.service

import com.example.storyapi_sm.domain.entity.Article
import com.example.storyapi_sm.repository.ArticleRepository
import com.example.storyapi_sm.repository.FileRepository
import com.example.util.exception.NileCommonError
import com.example.util.exception.NileException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleService {
    /*lateinit을 사용하면 변수의 값을 지정하는 작업을 뒤로 미룰 수 있다.
    not Nullable한 변수 + Assign하는 작업을 뒤로 미루고 싶을 때 lateinit 사용
    mutable 변수만 가능하기 때문에 var 키워드를 가진 변수에서만 사용이 가능하고,
    실행 중에 값을 변경할 필요가 있는 경우 유용하다 */

    @Autowired
    //SpringBoot에서 Autowired를 필드 주입이 아닌 생성자 주입으로 권고하긴 하지만
    // 이 문서에서는 필드 주입으로 구현되었다.
    // lateinit을 통해 의존성을 나중에 주입하겠다는 의미
    lateinit var articleRepository: ArticleRepository

    // create
    fun addArticle(article: Article) = articleRepository.save(article)

    // read
    fun getArticles()=articleRepository.findAllByIdIsNotNullOrderByOpenedAtDesc()
    fun getArticleBySlug(slug: String): Article {

        return articleRepository.findBySlug(slug) ?: throw NileException(NileCommonError.NOT_FOUND)
    }

    // update

    // delete
    fun removeArticleBySlug(slug: String){
        val nileArticle = articleRepository.findBySlug(slug)
        nileArticle?.let { articleRepository.delete(it)}
            ?: throw NileException(NileCommonError.NOT_FOUND)
    }


}

