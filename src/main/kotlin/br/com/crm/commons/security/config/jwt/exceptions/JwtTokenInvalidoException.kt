package br.com.crm.commons.security.config.jwt.exceptions

import org.springframework.security.core.AuthenticationException

class JwtTokenInvalidoException : AuthenticationException {
    constructor(msg: String) : super(msg)
}