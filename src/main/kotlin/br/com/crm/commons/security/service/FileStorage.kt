package br.com.crm.commons.security.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.*
import java.util.stream.Stream

interface FileStorage {
    fun store(file: MultipartFile) : UUID
    fun store(file: MultipartFile, uuid : UUID) : UUID
    fun loadFile(uuid: UUID): Resource
    fun deleteAll()
    fun loadFiles(): Stream<Path>
    fun init()
}