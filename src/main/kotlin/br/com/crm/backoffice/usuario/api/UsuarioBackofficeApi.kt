package br.com.crm.backoffice.usuario.api

import br.com.crm.backoffice.usuario.service.UsuarioBackofficeService
import br.com.crm.sistema.constantes.URL
import br.com.crm.usuario.dto.UsuarioDTO
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@Api
@RequestMapping(URL.API_USUARIO_BACKOFFICE)
class UsuarioBackofficeApi(val usuarioBackofficeService: UsuarioBackofficeService) {

    @PostMapping
    fun cadastrar(@RequestBody @Valid usuario: UsuarioDTO) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(usuarioBackofficeService.cadastrar(usuario))
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id:  UUID,@RequestBody @Valid usuario: UsuarioDTO) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(usuarioBackofficeService.atualizar(id, usuario))
    }

    @GetMapping
    fun listar() : ResponseEntity<List<UsuarioDTO>> {
        return ResponseEntity.ok(usuarioBackofficeService.listar())
    }

    @GetMapping("/{id}")
    fun buscarUm(@PathVariable id:  UUID) : ResponseEntity<UsuarioDTO> {
        return ResponseEntity.ok(usuarioBackofficeService.buscarUm(id))
    }

}