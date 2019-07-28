package br.com.crm.commons.security.config.jwt.provider

import br.com.crm.commons.security.config.UserDetailsServiceImpl
import br.com.crm.commons.security.config.jwt.exceptions.UsuarioDesabilitadoException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class LoginAuthenticationProvider(private val usuarioNameRepository: UserDetailsServiceImpl,
                                  private val encoder : BCryptPasswordEncoder = BCryptPasswordEncoder()) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        Assert.notNull(authentication, "Dados de autenticação não inseridos")

        val login = authentication.principal as String
        val senha = authentication.credentials as String

        //TODO UsuarioAuth
        val usuario = usuarioNameRepository.loadUserByUsername(login)

        if (!encoder.matches(senha, usuario.password)) {
            throw BadCredentialsException("Usuário ou senha estão incorretos")
        }

        if (!usuario.isEnabled) {
            throw UsuarioDesabilitadoException("Usuário não está habilitado")
        }

        return UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
