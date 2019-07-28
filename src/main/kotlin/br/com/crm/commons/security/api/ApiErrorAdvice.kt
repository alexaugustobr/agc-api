package br.com.crm.commons.security.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice(annotations = [RestController::class])
class ApiErrorAdvice {



    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleBindingErrors(e: HttpMessageNotReadableException, request : HttpServletRequest,
                            response : HttpServletResponse): ResponseEntity<Any> {

        /*{
            "timestamp": "2019-04-05T00:50:23.515+0000",
            "status": 400,
            "error": "Internal Server Error",
            "message": "Request processing failed; nested exception is org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [\"FK9TTMYRERRPY9L55JG8FJFPQVL: CORE.GRUPO FOREIGN KEY(TURMA_ID) REFERENCES CORE.TURMA(ID) ('c0d90f6e-047a-4f70-bae3-dfca79081b72')\"; SQL statement:\ninsert into grupo (data_criacao, data_exclusao, data_ultima_alteracao, excluido, usuario_criacao_id, usuario_exclusao_id, logo_hash, nome, status, tema, turma_id, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) [23506-199]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
            "path": "/api/home/v1/grupos"
        }*/

        val map = hashMapOf<String, Any>()

        map.put("timestamp", Date())
        map.put("status", HttpStatus.BAD_REQUEST.value())
        map.put("error", "Bad Request")
        map.put("message", e.localizedMessage)
        map.put("path", request.pathInfo)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON_UTF8).body(map)
    }
}