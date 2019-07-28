package br.com.crm.commons.security.config.jwt.model.token

import br.com.crm.commons.security.config.jwt.config.JwtSettings
import br.com.crm.commons.usuario.model.Usuario
import io.jsonwebtoken.Jwts
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtTokenFactory @Autowired
constructor(@field:Autowired
            private val settings: JwtSettings) {

    fun createAccessJwtToken(userContext: Usuario): AccessJwtToken {
        if (StringUtils.isBlank(userContext.username)) throw IllegalArgumentException("Não é possivel criar o token JWT sem nome de usuário")

        //TODO FIX ME ADICIONAR AUTHORITIES
        //if (userContext.authorities.isEmpty()) throw IllegalArgumentException("Usuario não tem permissões no perfil")

        val claims = Jwts.claims().setSubject(userContext.username)
        val roles = mutableListOf<String>()

        for (grantedAuthority in userContext.authorities) {
            roles.add(grantedAuthority.toString())
        }
        claims.put("scopes", roles)


        //TODO FIX ME
        val currentTime = LocalDateTime.now()

        val token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.tokenIssuer)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(Integer.valueOf(settings.tokenExpirationTime!!).toLong())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(settings.signatureAlgorithm, settings.tokenSigningKey)
                .compact()

        return AccessJwtToken(token, claims)
    }
}
