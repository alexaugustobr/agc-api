package br.com.crm.commons.security.config.jwt.exceptions

import org.springframework.security.authentication.AuthenticationServiceException


class MetodoAutenticacaoNaoSuportadoException(msg: String) : AuthenticationServiceException(msg) {
    companion object {
        private val serialVersionUID = 3705043083010304496L
    }
}
