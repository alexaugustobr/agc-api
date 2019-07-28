package br.com.crm.backoffice.usuario.model

import br.com.crm.commons.security.enumx.Role
import br.com.crm.commons.usuario.dto.UsuarioDTO
import br.com.crm.commons.usuario.model.Usuario
import com.google.common.collect.Sets
import javax.persistence.Entity

@Entity
open class UsuarioBackoffice(nome: String = "",
                             email: String = "",
                             ativo: Boolean = true,
                             senha: String  = "",
                             permissoes: MutableSet<Role> = mutableSetOf()) :
        Usuario(nome = nome, email = email, ativo = ativo, senha = senha, permissoes = permissoes) {

    override fun atualizar(usuarioDTO: UsuarioDTO) : UsuarioBackoffice {
        return super.atualizar(usuarioDTO) as UsuarioBackoffice
    }

    companion object {
        fun criarNovo(usuarioDTO: UsuarioDTO): UsuarioBackoffice {
            return UsuarioBackoffice.of(usuarioDTO).salvarSenhaCriptografada(usuarioDTO.senha!!) as UsuarioBackoffice
        }

        fun of (usuarioDTO: UsuarioDTO) : UsuarioBackoffice{
            return UsuarioBackoffice(
                    email = usuarioDTO.email,
                    nome = usuarioDTO.nome,
                    permissoes = Sets.newHashSet(Role.ROLE_BACKOFFICE)
            )
        }
    }

}