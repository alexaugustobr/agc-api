package br.com.crm.commons.security.config.jwt.model.token


enum class Scopes {
    REFRESH_TOKEN;

    fun authority(): String {
        return "ROLE_" + this.name
    }
}
