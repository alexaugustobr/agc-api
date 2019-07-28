package br.com.crm.commons.security.config.jwt.model

class JWTResponseToken {
    private var token: String? = null

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }
}