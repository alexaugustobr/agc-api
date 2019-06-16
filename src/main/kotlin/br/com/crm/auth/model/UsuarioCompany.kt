package br.com.crm.auth.model

import br.com.crm.auth.enumx.Role
import br.com.crm.usuario.model.Usuario
import javax.persistence.Entity

@Entity
open class UsuarioCompany(nome: String, email: String, ativo: Boolean, senha: String,
                          permissoes: MutableSet<Role>) :
        Usuario(nome = nome, email = email, ativo = ativo, senha = senha, permissoes = permissoes) {

}