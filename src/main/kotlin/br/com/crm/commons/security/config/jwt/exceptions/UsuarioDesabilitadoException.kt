package br.com.crm.commons.security.config.jwt.exceptions

import org.springframework.security.core.AuthenticationException

class UsuarioDesabilitadoException : AuthenticationException {

    constructor(msg: String, t: Throwable) : super(msg, t)

    constructor(msg: String) : super(msg)

    companion object {

        private val serialVersionUID = -5833779298861613046L
    }
}