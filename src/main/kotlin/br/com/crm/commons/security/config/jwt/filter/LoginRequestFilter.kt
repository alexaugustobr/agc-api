package br.com.crm.commons.security.config.jwt.filter

import br.com.crm.commons.security.config.jwt.model.LoginRequestTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginRequestFilter(defaultProcessUrl: String,
                         var successHandler2: AuthenticationSuccessHandler,
                         var failureHandler2: AuthenticationFailureHandler,
                         val objectMapper: ObjectMapper) : AbstractAuthenticationProcessingFilter(defaultProcessUrl) {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val loginRequestTO: LoginRequestTO?

        try {
            loginRequestTO = objectMapper.readValue(request.reader, LoginRequestTO::class.java)
        } catch (e: Exception) {
            throw AuthenticationServiceException("Impossivel serializar a resposta.", e)
        }

        if (loginRequestTO == null) {
            throw AuthenticationServiceException("Impossivel serializar a resposta.")
        }

        if (StringUtils.isBlank(loginRequestTO.email) || StringUtils.isBlank(loginRequestTO.senha)) {
            throw AuthenticationServiceException("Usuário ou senha não inseridos.")
        }

        val token = UsernamePasswordAuthenticationToken(loginRequestTO.email, loginRequestTO.senha)

        return this.authenticationManager.authenticate(token)
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        successHandler2.onAuthenticationSuccess(request, response, authResult)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        SecurityContextHolder.clearContext()
        failureHandler2.onAuthenticationFailure(request, response, failed)
    }
}
