package br.com.crm.backoffice.usuario.model

import br.com.crm.auth.enumx.Role
import br.com.crm.usuario.dto.UsuarioDTO
import br.com.crm.usuario.model.Usuario
import javax.persistence.Entity

@Entity
open class UsuarioBackoffice(nome: String, email: String, ativo: Boolean, senha: String,
                             permissoes: MutableSet<Role>) :
        Usuario(nome = nome, email = email, ativo = ativo, senha = senha, permissoes = permissoes) {

    override fun atualizar(usuarioDTO: UsuarioDTO) : UsuarioBackoffice {
        return super.atualizar(usuarioDTO) as UsuarioBackoffice
    }

    companion object {
        fun criarNovo(usuarioDTO: UsuarioDTO): UsuarioBackoffice {
            return Usuario.criarNovo(usuarioDTO) as UsuarioBackoffice
        }

        fun of (usuarioDTO: UsuarioDTO) : UsuarioBackoffice{
            return Usuario.of(usuarioDTO) as UsuarioBackoffice
        }
    }

}