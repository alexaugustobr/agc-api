package br.com.crm.backoffice.usuario.dao

import br.com.crm.backoffice.usuario.model.UsuarioBackoffice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioBackofficeRepository : JpaRepository<UsuarioBackoffice, UUID>{

}