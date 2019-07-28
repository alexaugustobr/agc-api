package br.com.crm.commons.security.config.jwt.config

import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtSettings {

    var signatureAlgorithm = SignatureAlgorithm.HS512

    @Value("\${token.expiration.time}")
    var tokenExpirationTime: String? = null

    @Value("\${token.issuer}")
    var tokenIssuer: String? = null

    @Value("\${token.signing.key}")
    var tokenSigningKey: String? = null
}
