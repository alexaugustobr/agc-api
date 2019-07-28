package br.com.crm.backoffice.usuario.api

import br.com.crm.backoffice.usuario.service.UsuarioBackofficeService
import br.com.crm.commons.api.AbstractApi
import br.com.crm.commons.usuario.dto.UsuarioDTO
import br.com.crm.commons.usuario.validator.CriacaoGroupValidator
import br.com.crm.commons.usuario.validator.EdicaoGroupValidator
import br.com.crm.system.constantes.URL
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Api
@RequestMapping(URL.API_USUARIO_BACKOFFICE)
@PreAuthorize("hasAnyRole('ROLE_BACKOFFICE')")
class UsuarioBackofficeApi(val service: UsuarioBackofficeService) : AbstractApi() {

    @PostMapping
    fun cadastrar(@RequestBody @Validated(CriacaoGroupValidator::class) usuario: UsuarioDTO) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(service.cadastrar(usuario))
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id:  UUID,@RequestBody @Validated(EdicaoGroupValidator::class) usuario: UsuarioDTO) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(service.atualizar(id, usuario))
    }

    @GetMapping
    fun listar() : ResponseEntity<List<UsuarioDTO>> {
        return ResponseEntity.ok(service.listar())
    }

    @GetMapping("/{id}")
    fun buscarUm(@PathVariable id:  UUID) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(service.buscarUm(id))
    }

}