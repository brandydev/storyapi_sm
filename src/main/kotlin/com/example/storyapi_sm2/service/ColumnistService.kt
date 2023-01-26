package com.example.storyapi_sm2.service

import com.example.storyapi_sm2.domain.entity.Columnist
import com.example.storyapi_sm2.repository.ColumnistRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class ColumnistService {
    @Autowired
    lateinit var columnistRepository: ColumnistRepository

    // create
    // Columnist의 JSON 덩어리 -> request
    fun addColumnistByName(request: Columnist): Columnist? {
        val targetColumnist = Columnist(
            request.name ?: "Null", // Entity에서 Nullable 설정X
            request.info ?: "Null",
            request.profileImage ?: "Null"
        )
        columnistRepository.save(targetColumnist)

        return targetColumnist
    }

    // read
    fun getColumnistByName(name: String): Columnist? {
        val isExist = columnistRepository.existsByName(name)
        return if (!isExist) {
            null
        } else {
            columnistRepository.findByName(name)
        }
    }

    // update
    fun updateColumnistByName(name: String, request: Columnist): Columnist? {
        val isExist = columnistRepository.existsByName(name)
        return if (!isExist) {
            null
        } else {
            removeColumnistByName(name)
            addColumnistByName(request)
        }
    }

    // delete
    fun removeColumnistByName(name: String): Columnist? {
        val isExist = columnistRepository.existsByName(name)
        return if (!isExist) {
            null
        } else {
            val targetColumnist = columnistRepository.findByName(name)
            columnistRepository.delete(targetColumnist)
            targetColumnist
        }
    }
}


