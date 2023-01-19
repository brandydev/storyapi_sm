package com.example.storyapi_sm.service

import com.example.storyapi_sm.domain.entity.File
import com.example.storyapi_sm.domain.entity.NileCollection
import com.example.storyapi_sm.repository.FileRepository
import com.example.storyapi_sm.util.exception.NileCommonError
import com.example.storyapi_sm.util.exception.NileException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FileService {

    @Autowired
    lateinit var fileRepository: FileRepository

    // create
    fun addFile(file: File) = fileRepository.save(file)

    // read
    fun getFile() = fileRepository.findAllByFileIdIsNotNull()
    fun getFilePage(pageable: Pageable) = fileRepository.findAllByFileIdIsNotNull(pageable)
    fun getAddressByText(text: String): File {
        return fileRepository.findAddressByStorage(text) ?: throw NileException(NileCommonError.NOT_FOUND)


    }

    // delete
    fun removeFileByText(text: String) {
        val nileFile = fileRepository.findAddressByStorage(text)
        nileFile?.let{fileRepository.delete(it)}
            ?: throw NileException(NileCommonError.NOT_FOUND)
    }

}