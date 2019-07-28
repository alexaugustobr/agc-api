package br.com.crm.commons.security.config.jwt.auth.extractor

import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.stereotype.Component

@Component
class JwtHeaderTokenExtractor : TokenExtractor {
    override fun extract(header: String): String {
        if (StringUtils.isBlank(header)) {
            throw AuthenticationServiceException("Authorization header cannot be blank!")
        }

        if (header.length < HEADER_PREFIX.length) {
            throw AuthenticationServiceException("Invalid authorization header size.")
        }

        return header.substring(HEADER_PREFIX.length, header.length)
    }

    override fun extract(payload: String, token: String): String {
        if (StringUtils.isBlank(payload)) {
            throw AuthenticationServiceException("Authorization header cannot be blank!")
        }

        if (payload.length < HEADER_PREFIX.length) {
            throw AuthenticationServiceException("Invalid authorization header size.")
        }
        return payload.substring(HEADER_PREFIX.length, payload.length)
    }

    companion object {
        var HEADER_PREFIX = "Bearer "
    }
}
