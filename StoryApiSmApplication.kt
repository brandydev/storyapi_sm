package com.example.storyapi_sm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class StoryApiSmApplication

fun main(args: Array<String>) {
    runApplication<StoryApiSmApplication>(*args)
}
