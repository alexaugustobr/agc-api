package br.com.crm.commons.security.config

import br.com.crm.commons.usuario.model.Usuario
import org.springframework.security.core.context.SecurityContextHolder

object UsuarioUtil {

    fun usuarioAutenticado() : Usuario? {
        val authentication = SecurityContextHolder.getContext().authentication ?: return null
        return authentication.principal as Usuario
    }

}