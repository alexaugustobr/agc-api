package br.com.crm.commons.security.config

import br.com.crm.commons.security.exception.UsuarioInativoException
import br.com.crm.commons.usuario.dao.UsuarioRepository
import br.com.crm.commons.usuario.model.Usuario
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserDetailsServiceImpl(val usuarioRepository : UsuarioRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class, UsuarioInativoException::class)
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    override fun loadUserByUsername(email: String): Usuario {

        val usuario = usuarioRepository.buscarUmComPermissoesPorEmail(email)

        if (!usuario.isPresent) throw UsernameNotFoundException(email)

        if (!usuario.get().isEnabled) throw BadCredentialsException("Usuário não está habilitado")

        //TODO FIX ME ADICIONAR AUTHORITIES
        //if (usuario.get().permissoes.isEmpty()) throw BadCredentialsException("Usuário não possui permissões")

        return usuario.get()
    }

}