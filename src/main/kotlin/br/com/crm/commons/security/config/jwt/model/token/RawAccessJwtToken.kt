package br.com.crm.commons.security.config.jwt.model.token

import br.com.crm.commons.security.config.jwt.exceptions.JwtTokenExpiradoException
import br.com.crm.commons.security.config.jwt.exceptions.JwtTokenInvalidoException
import io.jsonwebtoken.*

class RawAccessJwtToken(override val token: String) : JwtToken {

    fun parseClaims(signingKey: String?): Jws<Claims> {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token)
        } catch (ex: UnsupportedJwtException) {
            throw JwtTokenInvalidoException("Token inválido")
        } catch (ex: MalformedJwtException) {
            throw JwtTokenInvalidoException("Token inválido")
        } catch (ex: IllegalArgumentException) {
            throw JwtTokenInvalidoException("Token inválido")
        } catch (ex: SignatureException) {
            throw JwtTokenInvalidoException( "Token inválido")
        } catch (expiredEx: ExpiredJwtException) {
            throw JwtTokenExpiradoException("JWT Token expirado")
        }

    }

}