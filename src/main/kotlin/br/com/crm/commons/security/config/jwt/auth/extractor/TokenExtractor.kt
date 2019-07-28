package br.com.crm.commons.security.config.jwt.auth.extractor

interface TokenExtractor {
    fun extract(payload: String): String

    fun extract(payload: String, token: String): String
}
