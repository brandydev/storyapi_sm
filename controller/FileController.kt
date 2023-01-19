package com.example.storyapi_sm.controller

import com.example.storyapi_sm.domain.entity.File
import com.example.storyapi_sm.repository.FileRepository
import com.example.storyapi_sm.service.FileService
import com.example.storyapi_sm.util.exception.NileCommonError
import com.example.storyapi_sm.util.exception.NileException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class FileController {
    @Autowired
    lateinit var fileService: FileService

    @Autowired
    lateinit var fileRepository: FileRepository

    @GetMapping("/file")
    fun getFile(
        @RequestParam("address") address: String?,
        @RequestParam("slug") slug: String?,
    ) {
        if (slug.isNullOrBlank() && address.isNullOrBlank()) {
            throw NileException(NileCommonError.INVALID_PARAMETER)
        }

    }
}