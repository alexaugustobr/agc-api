package br.com.crm.system.runner

import br.com.crm.backoffice.usuario.model.UsuarioBackoffice
import br.com.crm.commons.security.enumx.Role
import br.com.crm.commons.usuario.dao.UsuarioRepository
import br.com.crm.company.usuario.model.UsuarioCompany
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
//@Profile(value = ["dev","default"])
class AppDevDatabaseRunner(val usuarioRepository: UsuarioRepository) : ApplicationRunner{

    private val logger = LoggerFactory.getLogger(AppDevDatabaseRunner::class.java)
    override fun run(args: ApplicationArguments?) = iniciarBanco()

    fun iniciarBanco() {
        logger.info("Criando banco de com dados.")

        if (!usuarioRepository.findAll().isEmpty()) {
            logger.info("Base já está criado.")
            return
        }

        val admin = UsuarioBackoffice(nome = "Administrador", email = "admin@email.com.br", senha = BCryptPasswordEncoder().encode("senha"), ativo = true, permissoes = mutableSetOf(Role.ROLE_BACKOFFICE))
        val company = UsuarioCompany(nome = "Company", email = "company@email.com.br", senha = BCryptPasswordEncoder().encode("senha"), ativo = true, permissoes = mutableSetOf(Role.ROLE_COMPANY))

        usuarioRepository.save(admin)
        usuarioRepository.save(company)

        logger.info("Finalizado setup dos dados simulados no banco")

        logger.info("Servidor iniciado.")
    }

}