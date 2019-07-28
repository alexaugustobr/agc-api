package br.com.crm.commons.usuario.dto

import br.com.crm.commons.security.enumx.Role
import br.com.crm.commons.usuario.model.Usuario
import br.com.crm.commons.usuario.validator.CriacaoGroupValidator
import br.com.crm.system.dto.DataTransferObject
import java.util.*
import javax.validation.constraints.NotBlank

data class UsuarioDTO (
        @get:NotBlank
        var nome  : String = "",

        @get:NotBlank
        //TODO @get:UniqueEmail
        var email : String = "",

        var ativo : Boolean = true,

        @get:NotBlank(groups = [CriacaoGroupValidator::class])
        var senha : String? = null,

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