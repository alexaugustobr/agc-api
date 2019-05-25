package br.com.ope.security

import br.com.ope.model.Usuario
import org.springframework.security.core.context.SecurityContextHolder

object UsuarioUtil {

    fun usuarioAutenticado() : Usuario? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        return authentication.principal as Usuario
    }

}