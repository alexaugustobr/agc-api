package br.com.crm.usuario.dto

import br.com.crm.auth.enumx.Role
import br.com.crm.sistema.dto.DataTransferObject
import br.com.crm.usuario.model.Usuario
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.validation.constraints.NotBlank

data class UsuarioDTO (
        @NotBlank
        var nome  : String = "",

        @NotBlank
        var email : String = "",

        var ativo : Boolean = true,

        @JsonIgnore
        @NotBlank
        var senha : String = "",

        var permissoes: MutableSet<Role> = mutableSetOf(),

        var id: UUID? = null,

        var dataCriacao : Date = Date()

) : DataTransferObject() {

        companion object {
            fun of(usuario: Usuario) : UsuarioDTO {
                    return UsuarioDTO(
                            id = usuario.id,
                            nome = usuario.nome,
                            email = usuario.email,
                            ativo = usuario.ativo,
                            permissoes = usuario.permissoes,
                            dataCriacao = usuario.dataCriacao
                    )
            }
        }

}