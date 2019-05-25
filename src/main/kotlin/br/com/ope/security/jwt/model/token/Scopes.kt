package br.com.ope.security.jwt.model.token


enum class Scopes {
    REFRESH_TOKEN;

    fun authority(): String {
        return "ROLE_" + this.name
    }
}
