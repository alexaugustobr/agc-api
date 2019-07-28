package br.com.crm.commons.usuario.dto

import br.com.crm.commons.security.config.UsuarioUtil
import br.com.crm.commons.usuario.model.Usuario
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.reflect.KClass

data class UsuarioAlteracaoSenhaDTO(@SenhaValidator(message = "Senha atual está incorreta.")
                                   @field:NotBlank
                                   @field:NotNull
                                   val senhaAtual : String = "",
                                    @field:Size(min=6, max = 20, message = "A nova senha deve conter de 6 a 20 caracteres.")
                                   @field:NotBlank
                                   @field:NotNull
                                   val novaSenha : String? = null) {

    @Target(AnnotationTarget.FIELD)
    @Retention(AnnotationRetention.RUNTIME)
    @Constraint(validatedBy = [SenhaValidatorImpl::class])
    @MustBeDocumented
    annotation class SenhaValidator(val message: String = "Senha atual incorreta!",
                                    val groups: Array<KClass<*>> = [],
                                    val payload: Array<KClass<out Payload>> = [])

    @Component
    class SenhaValidatorImpl : ConstraintValidator<SenhaValidator, String> {

        override fun isValid(senha: String?, context: ConstraintValidatorContext?): Boolean {
            if (StringUtils.isBlank(senha)) return false

            val usuarioAutenticado : Usuario = UsuarioUtil.usuarioAutenticado()!!

            return BCryptPasswordEncoder().matches(senha, usuarioAutenticado.password)
        }

    }

}