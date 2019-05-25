package br.com.ope.vo

import javax.validation.constraints.NotBlank

data class UsuarioEdicaoVO(@field:NotBlank val nome : String = "", @field:NotBlank val email : String = "")