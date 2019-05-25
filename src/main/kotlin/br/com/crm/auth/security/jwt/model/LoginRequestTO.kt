package br.com.crm.auth.security.jwt.model

class LoginRequestTO {
    var email: String? = null
    var senha: String? = null

    constructor()

    constructor(usuario: String, senha: String) {
        this.email = usuario
        this.senha = senha
    }
}
