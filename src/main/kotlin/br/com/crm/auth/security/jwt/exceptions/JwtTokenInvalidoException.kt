package br.com.crm.auth.security.jwt.exceptions

import org.springframework.security.core.AuthenticationException

class JwtTokenInvalidoException : AuthenticationException {
    constructor(msg: String) : super(msg)
}