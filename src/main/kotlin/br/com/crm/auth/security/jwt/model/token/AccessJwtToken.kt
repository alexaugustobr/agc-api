package br.com.crm.auth.security.jwt.model.token

import com.fasterxml.jackson.annotation.JsonIgnore
import io.jsonwebtoken.Claims

class AccessJwtToken(override val token: String, @field:JsonIgnore
val claims: Claims) : JwtToken
