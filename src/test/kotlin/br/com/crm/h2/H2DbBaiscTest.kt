package br.com.crm.h2

import br.com.crm.auth.security.jwt.model.LoginRequestTO
import br.com.crm.backoffice.usuario.service.UsuarioBackofficeService
import br.com.crm.usuario.dto.UsuarioDTO
import br.com.crm.usuario.model.Usuario
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@RunWith(SpringJUnit4ClassRunner::class)
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Ignore
//@PropertySource("src/test/resources/application-pg-test.properties")
abstract class H2DbBasicTest {

    @Autowired
    lateinit var mockMvc : MockMvc

    @Autowired
    lateinit var usuarioBackofficeService: UsuarioBackofficeService

    val adminBackoffice = UsuarioDTO(nome = "teste", email = "teste@email.com.br", senha = "senha")

    @Before
    fun cadastrarUsuarios() {

        usuarioBackofficeService.cadastrar(adminBackoffice)

    }

    protected fun json(o: Any): String {
        return ObjectMapper().writeValueAsString(o)
    }

    protected fun <T> toObject(s: String, clazz: Class<T>): T {
        return ObjectMapper().readValue(s, clazz)
    }

    protected fun login(usuario: UsuarioDTO): ResultActions {
        val input = LoginRequestTO(usuario.email, "senha")
        return this.login(input)
    }

    protected fun login(requestTO: LoginRequestTO): ResultActions {
        try {
            return mockMvc.perform(post("/api/v1/login").content(json(requestTO)).contentType(MediaType.APPLICATION_JSON))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    protected fun login(username: String, password: String): ResultActions {
        val input = LoginRequestTO(username, password)
        return this.login(input)
    }

    protected fun extrairToken(result: MvcResult): HttpHeaders {
        try {

            if (result.response.status != 200) {
                throw RuntimeException(result.response.contentAsString)
            }

            val header = HttpHeaders()

            val value = "Bearer " + JsonPath.read<Any>(result.response.contentAsString, "$.token")

            header.set("Authorization", value)

            return header

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    protected fun getLoginToken(username: String, password: String): HttpHeaders {
        return extrairToken(login(username, password).andReturn())
    }

    protected fun getLoginToken(usuario: Usuario): HttpHeaders {
        return this.getLoginToken(usuario.email, "senha")
    }

}