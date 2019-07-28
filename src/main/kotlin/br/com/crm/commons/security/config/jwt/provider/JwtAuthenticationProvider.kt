package br.com.crm.commons.security.config.jwt.provider

import br.com.crm.commons.security.config.UserDetailsServiceImpl
import br.com.crm.commons.security.config.jwt.auth.JwtAuthenticationToken
import br.com.crm.commons.security.config.jwt.config.JwtSettings
import br.com.crm.commons.security.config.jwt.exceptions.UsuarioDesabilitadoException
import br.com.crm.commons.security.config.jwt.model.token.RawAccessJwtToken
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtAuthenticationProvider(private val jwtSettings: JwtSettings, private val userDetailsService: UserDetailsServiceImpl) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val rawAccessToken = authentication.credentials as RawAccessJwtToken

        if (StringUtils.isBlank(rawAccessToken.token)) {
            return JwtAuthenticationToken(null, ArrayList())
        }

        val jwsClaims = rawAccessToken.parseClaims(jwtSettings.tokenSigningKey)
        val subject = jwsClaims.body.subject
        val scopes = jwsClaims.body.get("scopes", MutableList::class.java)
        val authorities : MutableSet<GrantedAuthority> = hashSetOf()
        for (scope in scopes) {
            authorities.add(SimpleGrantedAuthority(scope as String))
        }

        val usuario = userDetailsService.loadUserByUsername(subject)

        if (!usuario.isEnabled) {
            throw UsuarioDesabilitadoException("Usuário não está habilitado")
        }

        return JwtAuthenticationToken(usuario, authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
