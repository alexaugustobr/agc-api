package br.com.crm.h2

import br.com.crm.backoffice.usuario.model.UsuarioBackoffice
import br.com.crm.commons.usuario.model.Usuario
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class UsuarioBackofficeTest {



    @Test
    fun cast() {

        val a = UsuarioBackoffice()

        val b = a as Usuario



    }

}