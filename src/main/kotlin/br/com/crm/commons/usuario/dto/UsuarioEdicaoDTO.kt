package br.com.crm.commons.usuario.dto

import javax.validation.constraints.NotBlank

data class UsuarioEdicaoDTO(@field:NotBlank val nome : String = "", @field:NotBlank val email : String = "")