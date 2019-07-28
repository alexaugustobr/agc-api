package br.com.crm.commons.security.api

import br.com.crm.commons.security.config.jwt.auth.JwtAuthenticationToken
import br.com.crm.commons.security.config.jwt.model.JWTResponseToken
import br.com.crm.commons.security.config.jwt.model.LoginRequestTO
import br.com.crm.commons.usuario.model.Usuario
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1", "/api/v1"])
@Api(tags=["Auth"], description = "Autenticação")
class AuthApi {

    val userDetailsService : UserDetailsService

    constructor(userDetailsService: UserDetailsService) {
        this.userDetailsService = userDetailsService
    }

    @ApiOperation(
            value="Detalhes do usuário autenticado.",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            response= Usuario::class
    )
    @ApiResponses(value=[
        ApiResponse(
                code=200,
                message="Retorna os detalhes do usuário autenticado.",
                response= Usuario::class
        ),
        ApiResponse(
                code=204,
                message="Retorna resposta vazia."
        ),
        ApiResponse(
                code=400,
                message="Erro mapeado no servidor."
        ),
        ApiResponse(
                code=500,
                message="Erro inesperado no servidor."
        )
    ]
    )
    @GetMapping("/me","/eu")
    fun detalhesUsuarioAutenticado(token: JwtAuthenticationToken): ResponseEntity<*> {
        val userContext = token.principal as Usuario
        val usuario = userDetailsService.loadUserByUsername(userContext.email) ?: throw UsernameNotFoundException("Usuário não encontrado: " + userContext.email)
        return ResponseEntity.ok(usuario)
    }

    @ApiOperation(
            value="Essa operação gera um token JWT de autenticação.",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            response= JWTResponseToken::class
    )
    @ApiResponses(value=[
        ApiResponse(
                code=200,
                message="Retorna token gerado.",
                response= JWTResponseToken::class
        ),
        ApiResponse(
                code=204,
                message="Retorna resposta vazia."
        ),
        ApiResponse(
                code=400,
                message="Erro mapeado no servidor."
        ),
        ApiResponse(
                code=500,
                message="Erro inesperado no servidor."
        )
    ]
    )
    @PostMapping("/login")
    fun login(@RequestBody login: LoginRequestTO) = ResponseEntity.ok(JWTResponseToken())

}