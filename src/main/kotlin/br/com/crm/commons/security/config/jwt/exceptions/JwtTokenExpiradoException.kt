package br.com.crm.commons.security.config.jwt.exceptions

import org.springframework.security.core.AuthenticationException

class JwtTokenExpiradoException : AuthenticationException {
    constructor(msg: String) : super(msg)
}
