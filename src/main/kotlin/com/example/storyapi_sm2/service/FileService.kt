package com.example.storyapi_sm2.service

import com.example.storyapi_sm2.domain.entity.File
import com.example.storyapi_sm2.enums.Location
import com.example.storyapi_sm2.enums.Types
import com.example.storyapi_sm2.repository.FileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FileService {
    @Autowired
    lateinit var fileRepository: FileRepository

    // create
    fun addFileByName(request: File): File? {
        val targetFile = File(
          request.articleId,
            request.fileType ?: Types.OTHER,
            request.name ?: "Null",
            request.path ?: "Null",
            request.sizeType,
            request.location ?: Location.OTHER
        )
        fileRepository.save(targetFile)
        return targetFile
    }

    // read
    fun getFileByName(name: String): File? {
        val isExist = fileRepository.existsByName(name)
        return if (!isExist){

            null
        } else {
            fileRepository.findByName(name)
        }
    }

    // update
    fun updateFileByName(name: String, request: File): File? {
        val isExist = fileRepository.existsByName(name)
        return if (!isExist){

            null
        } else {
            removeFileByName(name)
            addFileByName(request)
        }
    }

    // delete
    fun removeFileByName(name: String): File? {
        val isExist = fileRepository.existsByName(name)
        return if (!isExist){

            null
        } else {

            val targetFile = fileRepository.findByName(name)
            fileRepository.delete(targetFile)
            targetFile
        }
    }
}