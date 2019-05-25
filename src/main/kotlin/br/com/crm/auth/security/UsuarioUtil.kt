package br.com.crm.auth.security

import br.com.crm.auth.model.Usuario
import org.springframework.security.core.context.SecurityContextHolder

object UsuarioUtil {

    fun usuarioAutenticado() : Usuario? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        return authentication.principal as Usuario
    }

}