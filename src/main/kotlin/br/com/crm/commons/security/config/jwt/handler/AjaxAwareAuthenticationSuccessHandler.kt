package br.com.crm.commons.security.config.jwt.handler

import br.com.crm.commons.security.config.jwt.model.token.JwtTokenFactory
import br.com.crm.commons.usuario.model.Usuario
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AjaxAwareAuthenticationSuccessHandler @Autowired
constructor(private val mapper: ObjectMapper, private val tokenFactory: JwtTokenFactory) : AuthenticationSuccessHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse,
                                         authentication: Authentication) {
        val userContext = authentication.principal as Usuario

        val accessToken = tokenFactory.createAccessJwtToken(userContext)

        val tokenMap = HashMap<String, String>()
        tokenMap["token"] = accessToken.token

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        mapper.writeValue(response.writer, tokenMap)

        clearAuthenticationAttributes(request)
    }


    protected fun clearAuthenticationAttributes(request: HttpServletRequest) {
        val session = request.getSession(false) ?: return

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }
}
