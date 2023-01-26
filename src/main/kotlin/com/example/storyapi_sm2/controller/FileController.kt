package com.example.storyapi_sm2.controller

import com.example.storyapi_sm2.domain.entity.File
import com.example.storyapi_sm2.repository.FileRepository
import com.example.storyapi_sm2.service.FileService
import com.example.storyapi_sm2.util.exception.NileCommonError
import com.example.storyapi_sm2.util.response.NileResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FileController {

    @Autowired
    lateinit var fileService: FileService

    // create : 파일 이름,경로,위치 적는 곳
    @PostMapping("/file")
    fun addFile(
        @RequestBody payload: File
    ): ResponseEntity<*> {
        if (payload.name.isBlank()) {
            return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_PARAMETER.getErrorCode(),
                    status = NileCommonError.INVALID_PARAMETER.getHttpStatus(),
                    message = "이미 존재하는 File의 이름입니다"
                )
            )
        }

        val createdFile: File? = fileService.addFileByName(payload)
        createdFile?.let {
            return ResponseEntity.ok(
                NileResponse(
                    message = "File이 성공적으로 업로드되었습니다",
                    result = createdFile
                )
            )
        } ?: return ResponseEntity.ok(
            NileResponse(
                errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                status = NileCommonError.INVALID_NAME.getHttpStatus(),
                message = "이미 존재하는 File입니다"
            )
        )
    }

        // read : 파일 이름 읽기
        @GetMapping("/file")
        fun getFile(
            @RequestParam("name", required = false, defaultValue = "") name: String
        ) : ResponseEntity<*> {
            if (name.isNullOrBlank()) {
                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                        status = NileCommonError.INVALID_NAME.getHttpStatus(),
                        message = "File 조회를 위해서는 File의 이름이 필요합니다"
                    )
                )
            }

            val selectedFile: File? = fileService.getFileByName(name)
            selectedFile?.let {
                return ResponseEntity.ok(
                    NileResponse(
                        message = "File이 성공적으로 조회되었습니다",
                        result = selectedFile
                    )

                )
            } ?: return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                    status = NileCommonError.INVALID_NAME.getHttpStatus(),
                    message = "존재하지 않는 File 입니다"
                )
            )
        }

        // update : 파일 정보 업데이트
        @PutMapping("/file")
        fun updateFile(
            @RequestParam("name", required = false, defaultValue = "") name: String,
            @RequestBody payload: File
        ): ResponseEntity<*> {
            if (name.isNullOrBlank()) {
                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                        status = NileCommonError.INVALID_NAME.getHttpStatus(),
                        message = "File의 정보를 수정하기 위해서는 File의 name 입력이 필요합니다"
                    )
                )
            }

            val updatedFile = fileService.updateFileByName(name, payload)
            updatedFile?.let {
                return ResponseEntity.ok(
                    NileResponse(
                        message = "File의 정보가 성공적으로 수정되었습니다",
                        result = updatedFile
                    )
                )
            } ?: return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.NOT_FOUND.getErrorCode(),
                    status = NileCommonError.NOT_FOUND.getHttpStatus(),
                    message = "존재하지 않는 File입니다"
                )
            )
        }

    // delete : 파일 삭제
        @DeleteMapping("/file")
        fun removeFile(
            @RequestParam("name", required = false, defaultValue = "") name: String?
    ): ResponseEntity<*> {
            if (name.isNullOrBlank()){
                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.NOT_FOUND.getErrorCode(),
                        status = NileCommonError.NOT_FOUND.getHttpStatus(),
                        message = "File 삭제를 위해서는 File의 name 입력이 필요합니다"
                    )
                )
        }

        val deletedFile: File? = fileService.removeFileByName(name)
        deletedFile?.let {
            return ResponseEntity.ok(
                NileResponse(
                    message = "File이 성공적으로 삭제되었습니다",
                    result = deletedFile
                )
            )
        } ?: return ResponseEntity.ok(
            NileResponse (
                errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
            status = NileCommonError.INVALID_NAME.getHttpStatus(),
            message = "존재하지 않는 File입니다"
        )
        )
    }
}