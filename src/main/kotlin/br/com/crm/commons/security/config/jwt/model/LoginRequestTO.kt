package br.com.crm.commons.security.config.jwt.model

class LoginRequestTO {
    var email: String? = null
    var senha: String? = null

    constructor()

    constructor(usuario: String, senha: String) {
        this.email = usuario
        this.senha = senha
    }
}
