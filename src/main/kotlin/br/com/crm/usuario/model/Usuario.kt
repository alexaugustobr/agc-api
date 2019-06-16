package br.com.crm.usuario.model

import br.com.crm.auth.enumx.Role
import br.com.crm.auth.vo.UsuarioEdicaoVO
import br.com.crm.db.model.AbstractModel
import br.com.crm.usuario.dto.UsuarioDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class Usuario(
        @NotBlank
        open var nome  : String = "",
        @Column(unique = true)
        @NotBlank(message = "Email é obrigatório.")
        open var email : String = "",
        open var ativo : Boolean = false,
        @JsonIgnore
        open var senha : String = "",
        @ElementCollection(targetClass = Role::class)
        @Enumerated(EnumType.STRING)
        open var permissoes: MutableSet<Role> = mutableSetOf(),
        id: UUID? = null,
        dataCriacao: Date = Date(),
        dataExclusao: Date? = null,
        excluido: Boolean = false
) : AbstractModel(id, dataCriacao, dataExclusao, excluido), UserDetails {

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableSetOf<GrantedAuthority>()

        for (permissao in permissoes) {
            authorities.add(SimpleGrantedAuthority(permissao.name))

        }

        return authorities
    }
    @JsonIgnore
    override fun isEnabled(): Boolean {
        return ativo && !excluido
    }
    @JsonIgnore
    override fun getUsername(): String {
        return email
    }
    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return ativo && !excluido
    }
    @JsonIgnore
    override fun getPassword(): String {
        return senha
    }
    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return ativo && !excluido
    }
    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return ativo && !excluido
    }
    @JsonIgnore
    open fun gerarSenhaPadrao() = BCryptPasswordEncoder().encode("senha")

    open fun parse(usuario: Usuario): Usuario {
        this.email = usuario.email
        this.nome = usuario.nome
        return this
    }

    fun parse(usuario: UsuarioEdicaoVO): Usuario {
        this.nome = usuario.nome
        this.email = usuario.email
        return this
    }

    open fun salvarSenhaCriptografada(senha: String): Usuario {
        this.senha = BCryptPasswordEncoder().encode(senha)
        return this
    }

    open fun atualizar(usuarioDTO: UsuarioDTO) : Usuario {
        this.email = email
        this.nome = nome
        return this
    }

}