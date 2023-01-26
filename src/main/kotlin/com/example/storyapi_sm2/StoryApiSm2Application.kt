package com.example.storyapi_sm2

import com.example.storyapi_sm2.domain.entity.Columnist
import com.example.storyapi_sm2.domain.entity.File
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class StoryApiSm2Application{



}

fun main(args: Array<String>) {
    runApplication<StoryApiSm2Application>(*args)
}
