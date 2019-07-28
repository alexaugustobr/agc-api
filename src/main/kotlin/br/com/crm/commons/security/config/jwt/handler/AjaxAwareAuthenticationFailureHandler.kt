package br.com.crm.commons.security.config.jwt.handler

import br.com.crm.commons.security.config.jwt.exceptions.JwtTokenExpiradoException
import br.com.crm.commons.security.config.jwt.exceptions.JwtTokenInvalidoException
import br.com.crm.commons.security.config.jwt.exceptions.UsuarioDesabilitadoException
import br.com.crm.commons.security.config.jwt.model.ErrorCode
import br.com.crm.commons.security.config.jwt.model.ErrorResponseTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AjaxAwareAuthenticationFailureHandler @Autowired
constructor(private val mapper: ObjectMapper) : AuthenticationFailureHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException) {

        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        if (e is JwtTokenExpiradoException) {
            mapper.writeValue(response.writer, ErrorResponseTO.of("Token expirado", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED))
        } else if (e is UsuarioDesabilitadoException) {
            mapper.writeValue(response.writer, ErrorResponseTO.of("Usuário está desabilitado", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else if (e is JwtTokenInvalidoException) {
            mapper.writeValue(response.writer, ErrorResponseTO.of("Token inválido", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else if (e is BadCredentialsException) {
            mapper.writeValue(response.writer, ErrorResponseTO.of("Usuario ou senha incorretos", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else {
            mapper.writeValue(response.writer, ErrorResponseTO.of("Falha de autenticação", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        }
    }

}