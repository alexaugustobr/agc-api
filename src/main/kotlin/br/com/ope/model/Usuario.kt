package br.com.ope.model

import br.com.ope.enumx.Role
import br.com.ope.vo.UsuarioEdicaoVO
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
open class Usuario : AbstractModel, UserDetails {

    @NotBlank
    var nome  : String = ""
    @Column(unique = true)
    @NotBlank(message = "Email é obrigatório.")
    var email : String = ""
    var ativo : Boolean = false
    @JsonIgnore
    var senha : String = ""
    @ElementCollection(targetClass = Role::class)
    @Enumerated(EnumType.STRING)
    var permissoes: MutableSet<Role> = mutableSetOf()

    constructor() : super()



    constructor(nome: String) : super() {
        this.nome = nome
    }

    constructor(id: UUID? = null, nome: String) : super(id) {
        this.nome = nome
    }

    constructor(nome: String, email: String, ativo: Boolean, senha: String, permissoes: MutableSet<Role>) : super() {
        this.nome = nome
        this.email = email
        this.ativo = ativo
        this.senha = senha
        this.permissoes = permissoes
    }

    constructor(email: String, authorities: MutableSet<GrantedAuthority>) : super() {
        this.email = email
        for (authority in authorities) {
            permissoes.add(Role.valueOf(authority.authority))
        }

    }

    constructor(id: UUID?) : super(id)

    constructor(permissoes : MutableSet<Role>) : super() {
        this.permissoes = permissoes
    }

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

    open fun getPainelUrl() = "painel"

    open fun getSenhaPadrao() = BCryptPasswordEncoder().encode("senha")

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

}