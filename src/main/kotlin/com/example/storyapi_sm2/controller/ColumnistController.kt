package com.example.storyapi_sm2.controller

import com.example.storyapi_sm2.domain.entity.Columnist
import com.example.storyapi_sm2.service.ColumnistService
import com.example.storyapi_sm2.util.exception.NileCommonError
import com.example.storyapi_sm2.util.response.NileResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// payload는 JSON 데이터들의 묶음(난 이렇게 데이터 저장할거야) -> POSTMAN 기억!

@RestController
class ColumnistController {
    @Autowired
    lateinit var columnistService: ColumnistService

    // create (새로 저장할 예정인 JSON)
    // 칼럼니스트이름,정보,프로필이미지 적는 곳
    @PostMapping("/columnist") // ResponseEntity<*>는 Postman에 보여지는 응답의 형식을 정하는 Entity
    fun addColumnist(
        @RequestBody payload: Columnist
    ): ResponseEntity<*> {
        if (payload.name.isBlank()) {
            return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_PARAMETER.getErrorCode(),
                    status = NileCommonError.INVALID_PARAMETER.getHttpStatus(),
                    message = "이미 존재하는 Columnist의 이름입니다"
                )
            )
        }

        val createdColumnist: Columnist? = columnistService.addColumnistByName(payload)
        createdColumnist?.let {
            return ResponseEntity.ok(
                NileResponse(
                    message = "Columnist가 성공적으로 생성되었습니다",
                    result = createdColumnist
                )
            )
        } ?: return ResponseEntity.ok(
            NileResponse(
                errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                status = NileCommonError.INVALID_NAME.getHttpStatus(),
                message = "이미 존재하는 Columnist입니다"
            )
        )
    }

        //read
        @GetMapping("/columnist")
        fun getColumnist(
            @RequestParam("name", required = false, defaultValue = "") name: String?
        ): ResponseEntity<*> {
            if (name.isNullOrBlank()) {
                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                        status = NileCommonError.INVALID_NAME.getHttpStatus(),
                        message = "Columnist 조회를 위해서는 name 입력이 필요합니다"
                    )
                )
            }
            val selectedColumnist: Columnist? = columnistService.getColumnistByName(name)
            selectedColumnist?.let {
                return ResponseEntity.ok(
                    NileResponse(
                        message = "Columnist가 성공적으로 조회되었습니다",
                        result = selectedColumnist
                    )
                )
            } ?: return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                    status = NileCommonError.INVALID_NAME.getHttpStatus(),
                    message = "존재하지 않는 Columnist입니다"
                )
            )
        }


        // update
        // 칼럼니스트 정보 업데이트 -> 수정하는 함수 하나 만들고, 수정할때 이름/정보/프로필이미지 (service)
        @PutMapping("/columnist")
        fun updateColumnist(
            // RequestParam은 localhost:8080/name?Tom 이런식(postman)
            @RequestParam("name", required = false, defaultValue = "") name: String?,
            @RequestBody payload: Columnist
        ): ResponseEntity<*> {
            if (name.isNullOrBlank()) {

                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                        status = NileCommonError.INVALID_NAME.getHttpStatus(),
                        message = "Columnist의 정보를 수정하기 위해서는 Columnist의 name 입력이 필요합니다"
                    )
                )

            }

            val updatedColumnist = columnistService.updateColumnistByName(name, payload)
            updatedColumnist?.let {
                return ResponseEntity.ok(
                    NileResponse(
                        message = "Columnist의 정보가 성공적으로 수정되었습니다",
                        result = updatedColumnist
                    )
                )
            } ?: return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.NOT_FOUND.getErrorCode(),
                    status = NileCommonError.NOT_FOUND.getHttpStatus(),
                    message = "존재하지 않는 Columnist입니다"
                )
            )
        }


        // delete
        // 칼럼니스트 삭제 -> 이름/정보/프로필이미지 전부 다 삭제
        @DeleteMapping("/columnist")
        fun removeColumnist(
            @RequestParam("name", required = false, defaultValue = "") name: String?
        ): ResponseEntity<*> {

            if (name.isNullOrBlank()) {
                return ResponseEntity.ok(
                    NileResponse(
                        errorCode = NileCommonError.NOT_FOUND.getErrorCode(),
                        status = NileCommonError.NOT_FOUND.getHttpStatus(),
                        message = "Columnist 삭제를 위해서는 Columnist의 name 입력이 필요합니다"
                    )
                )
            }

            val deletedColumnist: Columnist? = columnistService.removeColumnistByName(name)
            deletedColumnist?.let {
                return ResponseEntity.ok(
                    NileResponse(
                        message = "Columnist가 성공적으로 삭제되었습니다",
                        result = deletedColumnist
                    )
                )
            } ?: return ResponseEntity.ok(
                NileResponse(
                    errorCode = NileCommonError.INVALID_NAME.getErrorCode(),
                    status = NileCommonError.INVALID_NAME.getHttpStatus(),
                    message = "존재하지 않는 Columnist 입니다"
                )
            )
        }
    }


