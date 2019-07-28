package br.com.crm.commons.security.config.jwt.model.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import java.util.*

class RefreshToken private constructor(val claims: Jws<Claims>) : JwtToken {

    override val token: String
        get() = ""

    val jti: String
        get() = claims.body.id

    val subject: String
        get() = claims.body.subject

    companion object {

        fun create(token: RawAccessJwtToken, signingKey: String): Optional<RefreshToken> {
            val claims = token.parseClaims(signingKey)

            val scopes = claims.body.get("scopes", MutableList::class.java)
            //TODO FIX ME
            return if (scopes == null || scopes.isEmpty()
                    || !scopes.stream().filter { scope -> Scopes.REFRESH_TOKEN.authority() == scope }.findFirst().isPresent) {
                Optional.empty()
            } else Optional.of(RefreshToken(claims))

        }
    }
}
