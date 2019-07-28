package br.com.crm.commons.usuario.dao

import br.com.crm.commons.usuario.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, UUID> {

    @Query("FROM Usuario usuario LEFT JOIN FETCH usuario.permissoes WHERE usuario.email = :email")
    fun buscarUmComPermissoesPorEmail(@Param("email") email: String) : Optional<Usuario>
}