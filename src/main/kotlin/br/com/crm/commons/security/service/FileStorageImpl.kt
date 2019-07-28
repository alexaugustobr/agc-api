package br.com.crm.commons.security.service

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Stream

@Service
class FileStorageImpl: FileStorage{

    val log = LoggerFactory.getLogger(this::class.java)
    val rootLocation = Paths.get("/tmp/crm")

    override fun store(file: MultipartFile, uuid :UUID) : UUID {
        createTempDirIfNotExists()
        val uri = this.rootLocation.resolve(uuid.toString())
        Files.copy(file.inputStream, uri)
        return uuid
    }

    override fun store(file: MultipartFile) : UUID {
        return store(file, UUID.randomUUID())
    }

    override fun loadFile(uuid: UUID): Resource {
        val file = rootLocation.resolve(uuid.toString())
        val resource = UrlResource(file.toUri())

        if(resource.exists() || resource.isReadable) {
            return resource
        }else{
            throw RuntimeException("FAIL!")
        }
    }

    override fun deleteAll(){
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun loadFiles(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
                .filter{path -> !path.equals(this.rootLocation)}
                .map(this.rootLocation::relativize)
    }

    override fun init() {
        createTempDirIfNotExists()
    }

    private fun createTempDirIfNotExists() {
        if (!File(rootLocation.toUri()).exists()) Files.createDirectory(rootLocation)
    }
}