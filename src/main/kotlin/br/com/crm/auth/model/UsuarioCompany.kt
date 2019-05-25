package br.com.crm.auth.model

import br.com.crm.auth.enumx.Role
import javax.persistence.Entity

@Entity
open class UsuarioCompany : Usuario {

    constructor() : super()

    constructor(
            nome: String,
            email: String,
            ativo: Boolean,
            senha: String,
            permissoes: MutableSet<Role>

    ) : super(nome, email, ativo, senha, permissoes) {
            this.nome = nome
            this.email = email
            this.ativo = ativo
            this.senha = senha
            this.permissoes = permissoes
    }
}