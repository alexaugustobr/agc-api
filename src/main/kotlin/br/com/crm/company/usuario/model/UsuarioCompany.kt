package br.com.crm.company.usuario.model

import br.com.crm.commons.security.enumx.Role
import br.com.crm.commons.usuario.model.Usuario
import javax.persistence.Entity

@Entity
open class UsuarioCompany(nome: String, email: String, ativo: Boolean, senha: String,
                          permissoes: MutableSet<Role>) :
        Usuario(nome = nome, email = email, ativo = ativo, senha = senha, permissoes = permissoes) {

}