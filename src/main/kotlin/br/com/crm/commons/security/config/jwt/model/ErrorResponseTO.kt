package br.com.crm.commons.security.config.jwt.model

import org.springframework.http.HttpStatus
import java.util.*


class ErrorResponseTO protected constructor(val message: String, val errorCode: ErrorCode, private val status: HttpStatus) {

    val timestamp: Date

    init {
        this.timestamp = Date()
    }

    fun getStatus(): Int? {
        return status.value()
    }

    companion object {

        fun of(message: String, errorCode: ErrorCode, status: HttpStatus): ErrorResponseTO {
            return ErrorResponseTO(message, errorCode, status)
        }
    }
}
