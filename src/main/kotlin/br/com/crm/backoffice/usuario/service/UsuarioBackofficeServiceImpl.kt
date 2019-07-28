package br.com.crm.backoffice.usuario.service

import br.com.crm.backoffice.usuario.dao.UsuarioBackofficeRepository
import br.com.crm.backoffice.usuario.model.UsuarioBackoffice
import br.com.crm.commons.usuario.dto.UsuarioDTO
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@Service
class UsuarioBackofficeServiceImpl(val usuarioBackofficeRepository: UsuarioBackofficeRepository) : UsuarioBackofficeService {

    override fun cadastrar(usuarioDto: @Valid UsuarioDTO): UsuarioDTO {

        val usuario = UsuarioBackoffice.criarNovo(usuarioDto)

        usuarioBackofficeRepository.save(usuario)

        return UsuarioDTO.of(usuario)
    }

    override fun atualizar(id: UUID, usuarioDto: @Valid UsuarioDTO): UsuarioDTO {

        val usuario = usuarioBackofficeRepository.findById(id)
                .orElseThrow { NotFoundException("Usuário de id: $id não foi encontrado!") }

        return UsuarioDTO.of(usuarioBackofficeRepository.save(usuario.atualizar(usuarioDto)))
    }

    override fun listar(): List<UsuarioDTO> {
        return usuarioBackofficeRepository.findAll()
                .stream()
                .map(UsuarioDTO.Companion::of)
                .collect(Collectors.toList())
    }

    override fun buscarUm(id : UUID): UsuarioDTO {
        return UsuarioDTO.of(usuarioBackofficeRepository.findById(id)
                .orElseThrow { NotFoundException("Usuário de id: $id não foi encontrado!") }
        )
    }

}