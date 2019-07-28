package br.com.crm.commons.security.config.jwt.auth

import br.com.crm.commons.security.config.jwt.model.token.RawAccessJwtToken
import br.com.crm.commons.usuario.model.Usuario
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken : AbstractAuthenticationToken {

    var rawAccessToken: RawAccessJwtToken? = null
    var userContext: Usuario? = null

    constructor(unsafeToken: RawAccessJwtToken) : super(null) {
        this.rawAccessToken = unsafeToken
        this.isAuthenticated = false
    }

    constructor(userContext: Usuario?, authorities: Collection<GrantedAuthority>) : super(authorities) {
        this.eraseCredentials()
        this.userContext = userContext
        super.setAuthenticated(true)
    }

    override fun setAuthenticated(authenticated: Boolean) {
        if (authenticated) {
            throw IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead")
        }

        super.setAuthenticated(false)
    }

    override fun getCredentials(): Any? {
        return rawAccessToken
    }

    override fun getPrincipal(): Usuario? {
        return this.userContext
    }

    override fun eraseCredentials() {
        super.eraseCredentials()
        this.rawAccessToken = null
    }

    companion object {
        private val serialVersionUID = 2877954820905567501L
    }
}
