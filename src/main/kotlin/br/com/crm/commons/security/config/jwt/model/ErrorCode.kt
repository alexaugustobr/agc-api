package br.com.crm.commons.security.config.jwt.model

import com.fasterxml.jackson.annotation.JsonValue

enum class ErrorCode(@get:JsonValue
                                         val errorCode: Int) {
    GLOBAL(2), AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11)
}
