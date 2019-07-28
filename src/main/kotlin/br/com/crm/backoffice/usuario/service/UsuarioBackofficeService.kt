package br.com.crm.backoffice.usuario.service

import br.com.crm.commons.usuario.dto.UsuarioDTO
import java.util.*
import javax.validation.Valid

interface UsuarioBackofficeService {

    fun cadastrar(usuarioDto:  UsuarioDTO): UsuarioDTO

    fun listar(): List<UsuarioDTO>

    fun buscarUm(id : UUID): UsuarioDTO

    fun atualizar(id: UUID, usuarioDto: @Valid UsuarioDTO): UsuarioDTO

}